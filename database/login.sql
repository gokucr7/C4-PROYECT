-- =========================================
-- BASE DE DATOS LOGIN (C4 PROJECT)
-- Estructura optimizada y normalizada
-- =========================================
DROP SCHEMA IF EXISTS login;
CREATE SCHEMA login
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_0900_ai_ci;
USE login;

-- =========================================
-- CONFIGURACIÓN SEGURA
-- =========================================
SET SESSION sql_mode = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- =========================================
-- 1) CATÁLOGOS
-- =========================================
CREATE TABLE rol (
  id     BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  codigo VARCHAR(32)  NOT NULL UNIQUE,      -- ADMIN, DOCENTE, ESTUDIANTE
  nombre VARCHAR(64)  NOT NULL
) ENGINE=InnoDB;

CREATE TABLE programa (
  id     BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  codigo VARCHAR(16)  NOT NULL UNIQUE,      -- p.ej. SIS, IND, ADM
  nombre VARCHAR(120) NOT NULL
) ENGINE=InnoDB;

-- Semillas iniciales
INSERT INTO rol(codigo,nombre) VALUES
  ('ADMIN','Administrador'),
  ('DOCENTE','Docente'),
  ('ESTUDIANTE','Estudiante');

INSERT INTO programa(codigo,nombre) VALUES
  ('SIS','Ingeniería de Sistemas'),
  ('ELE','Ingeniería Electrónica'),
  ('ADM','Administración de Empresas'),
  ('AGR','Agronomía'),
  ('ZOO','Zootecnia'),
  ('CON','Contaduría Pública'),
  ('SOC','Licenciatura en Sociales'),
  ('IND','Ingeniería Industrial'),
  ('MEC','Ingeniería Mecatrónica');

-- =========================================
-- 2) TABLA USUARIOS
-- =========================================
CREATE TABLE usuarios (
  id                   BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  ingresoUsuario       VARCHAR(120) NOT NULL UNIQUE,
  ingresoContrasenia   VARCHAR(255) NOT NULL,
  tipo_de_usuario      VARCHAR(32)  NOT NULL,
  activo               TINYINT(1)   NOT NULL DEFAULT 1,
  ultimo_acceso        DATETIME     NULL,
  creado_en            DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  actualizado_en       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT fk_usuarios_tipo_de_usuario
    FOREIGN KEY (tipo_de_usuario) REFERENCES rol(codigo)
      ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB;

CREATE INDEX idx_usuarios_tipo_de_usuario ON usuarios(tipo_de_usuario);

-- =========================================
-- 3) TABLA ALUMNOS
-- =========================================
CREATE TABLE alumnos (
  id              BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  nombres         VARCHAR(120) NOT NULL,
  apellidos       VARCHAR(120) NOT NULL,
  promedio        DECIMAL(4,2) NOT NULL DEFAULT 0.00,
  carrera         VARCHAR(16)  NOT NULL,
  creado_en       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  actualizado_en  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT ck_alumnos_promedio CHECK (promedio >= 0.00 AND promedio <= 5.00),
  CONSTRAINT fk_alumnos_carrera
    FOREIGN KEY (carrera) REFERENCES programa(codigo)
      ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB;

CREATE INDEX idx_alumnos_carrera ON alumnos(carrera);

CREATE TABLE periodo_academico (
  id         BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  codigo     VARCHAR(32)  NOT NULL UNIQUE,
  nombre     VARCHAR(120) NOT NULL,
  fecha_inicio DATE       NOT NULL,
  fecha_fin    DATE       NOT NULL
) ENGINE=InnoDB;

CREATE TABLE asignatura (
  id         BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  codigo     VARCHAR(32)  NOT NULL UNIQUE,
  nombre     VARCHAR(160) NOT NULL,
  programa   VARCHAR(16)  NOT NULL,
  CONSTRAINT fk_asignatura_programa FOREIGN KEY (programa) REFERENCES programa(codigo)
    ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB;

CREATE TABLE matricula (
  id             BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  alumno_id      BIGINT UNSIGNED NOT NULL,
  asignatura_id  BIGINT UNSIGNED NOT NULL,
  periodo_id     BIGINT UNSIGNED NOT NULL,
  fecha_registro DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_matricula_alumno FOREIGN KEY (alumno_id) REFERENCES alumnos(id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_matricula_asignatura FOREIGN KEY (asignatura_id) REFERENCES asignatura(id)
    ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT fk_matricula_periodo FOREIGN KEY (periodo_id) REFERENCES periodo_academico(id)
    ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT uk_matricula UNIQUE (alumno_id, asignatura_id, periodo_id)
) ENGINE=InnoDB;

CREATE TABLE nota (
  id             BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  matricula_id   BIGINT UNSIGNED NOT NULL,
  descripcion    VARCHAR(120)    NOT NULL,
  valor          DECIMAL(4,2)    NOT NULL CHECK (valor >= 0.00 AND valor <= 5.00),
  registrado_en  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_nota_matricula FOREIGN KEY (matricula_id) REFERENCES matricula(id)
    ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE INDEX idx_matricula_alumno ON matricula(alumno_id);
CREATE INDEX idx_matricula_periodo ON matricula(periodo_id);
CREATE INDEX idx_nota_matricula ON nota(matricula_id);

CREATE TABLE auditoria_cambios (
  id               BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  usuario          VARCHAR(120) NOT NULL,
  tabla_afectada   VARCHAR(64)  NOT NULL,
  accion           VARCHAR(16)  NOT NULL,
  registro_id      BIGINT UNSIGNED,
  valores_anteriores JSON NULL,
  valores_nuevos     JSON NULL,
  creado_en        DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

DELIMITER $$

CREATE TRIGGER trg_usuarios_insert
AFTER INSERT ON usuarios
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_cambios(usuario, tabla_afectada, accion, registro_id, valores_anteriores, valores_nuevos)
  VALUES (CURRENT_USER(), 'usuarios', 'INSERT', NEW.id, NULL,
          JSON_OBJECT('ingresoUsuario', NEW.ingresoUsuario, 'tipo_de_usuario', NEW.tipo_de_usuario, 'activo', NEW.activo));
END $$

CREATE TRIGGER trg_usuarios_update
AFTER UPDATE ON usuarios
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_cambios(usuario, tabla_afectada, accion, registro_id, valores_anteriores, valores_nuevos)
  VALUES (CURRENT_USER(), 'usuarios', 'UPDATE', NEW.id,
          JSON_OBJECT('ingresoUsuario', OLD.ingresoUsuario, 'tipo_de_usuario', OLD.tipo_de_usuario, 'activo', OLD.activo),
          JSON_OBJECT('ingresoUsuario', NEW.ingresoUsuario, 'tipo_de_usuario', NEW.tipo_de_usuario, 'activo', NEW.activo));
END $$

CREATE TRIGGER trg_usuarios_delete
AFTER DELETE ON usuarios
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_cambios(usuario, tabla_afectada, accion, registro_id, valores_anteriores, valores_nuevos)
  VALUES (CURRENT_USER(), 'usuarios', 'DELETE', OLD.id,
          JSON_OBJECT('ingresoUsuario', OLD.ingresoUsuario, 'tipo_de_usuario', OLD.tipo_de_usuario, 'activo', OLD.activo), NULL);
END $$

CREATE TRIGGER trg_alumnos_insert
AFTER INSERT ON alumnos
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_cambios(usuario, tabla_afectada, accion, registro_id, valores_anteriores, valores_nuevos)
  VALUES (CURRENT_USER(), 'alumnos', 'INSERT', NEW.id, NULL,
          JSON_OBJECT('nombres', NEW.nombres, 'apellidos', NEW.apellidos, 'promedio', NEW.promedio, 'carrera', NEW.carrera));
END $$

CREATE TRIGGER trg_alumnos_update
AFTER UPDATE ON alumnos
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_cambios(usuario, tabla_afectada, accion, registro_id, valores_anteriores, valores_nuevos)
  VALUES (CURRENT_USER(), 'alumnos', 'UPDATE', NEW.id,
          JSON_OBJECT('nombres', OLD.nombres, 'apellidos', OLD.apellidos, 'promedio', OLD.promedio, 'carrera', OLD.carrera),
          JSON_OBJECT('nombres', NEW.nombres, 'apellidos', NEW.apellidos, 'promedio', NEW.promedio, 'carrera', NEW.carrera));
END $$

CREATE TRIGGER trg_alumnos_delete
AFTER DELETE ON alumnos
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_cambios(usuario, tabla_afectada, accion, registro_id, valores_anteriores, valores_nuevos)
  VALUES (CURRENT_USER(), 'alumnos', 'DELETE', OLD.id,
          JSON_OBJECT('nombres', OLD.nombres, 'apellidos', OLD.apellidos, 'promedio', OLD.promedio, 'carrera', OLD.carrera), NULL);
END $$

DELIMITER ;

-- =========================================
-- 4) USUARIO ADMINISTRADOR POR DEFECTO
-- =========================================
-- Contraseña 'admin123' cifrada con bcrypt (puedes cambiarla cuando gustes)
INSERT INTO usuarios (ingresoUsuario, ingresoContrasenia, tipo_de_usuario)
VALUES (
  'admin',
  '$2y$10$E9nPo.Eys2SbkDXOiv1sEuLT4Va2eG5n0UZANUeAFX3K8t1Bz88.i', -- admin123
  'ADMIN'
);

-- =========================================
-- LISTO
-- =========================================
SELECT '✅ Base de datos LOGIN creada correctamente con usuario ADMIN listo para usar.' AS resultado;
