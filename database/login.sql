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

CREATE TABLE estado_alumno (
  id     BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  codigo VARCHAR(16)  NOT NULL UNIQUE,
  nombre VARCHAR(120) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE facultad (
  id     BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  codigo VARCHAR(16)  NOT NULL UNIQUE,
  nombre VARCHAR(160) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE programa (
  id     BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  codigo VARCHAR(16)  NOT NULL UNIQUE,      -- p.ej. SIS, IND, ADM
  nombre VARCHAR(120) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE facultad_programa (
  facultad_id BIGINT UNSIGNED NOT NULL,
  programa_id BIGINT UNSIGNED NOT NULL,
  fecha_asignacion DATE NOT NULL DEFAULT (CURRENT_DATE),
  PRIMARY KEY (facultad_id, programa_id),
  CONSTRAINT fk_facultad_programa_facultad
    FOREIGN KEY (facultad_id) REFERENCES facultad(id)
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_facultad_programa_programa
    FOREIGN KEY (programa_id) REFERENCES programa(id)
      ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

-- Semillas iniciales
INSERT INTO rol(codigo,nombre) VALUES
  ('ADMIN','Administrador'),
  ('DOCENTE','Docente'),
  ('ESTUDIANTE','Estudiante');

INSERT INTO estado_alumno(codigo,nombre) VALUES
  ('ACTIVO','Alumno activo'),
  ('INACTIVO','Alumno inactivo'),
  ('GRADUADO','Alumno graduado'),
  ('SUSPENDIDO','Alumno suspendido');

INSERT INTO facultad(codigo,nombre) VALUES
  ('FING','Facultad de Ingeniería'),
  ('FADM','Facultad de Administración'),
  ('FAGR','Facultad de Ciencias Agrarias');

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

INSERT INTO facultad_programa(facultad_id, programa_id, fecha_asignacion)
SELECT f.id, p.id, CURRENT_DATE
  FROM programa p
  JOIN facultad f ON (
      (p.codigo IN ('SIS','ELE','IND','MEC') AND f.codigo = 'FING') OR
      (p.codigo IN ('ADM','CON') AND f.codigo = 'FADM') OR
      (p.codigo IN ('AGR','ZOO','SOC') AND f.codigo = 'FAGR')
  );

-- =========================================
-- 2) USUARIOS Y PERFILES
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

CREATE TABLE usuario_perfil (
  id            BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  usuario_id    BIGINT UNSIGNED NOT NULL,
  nombres       VARCHAR(120) NOT NULL,
  apellidos     VARCHAR(120) NOT NULL,
  correo        VARCHAR(160) NOT NULL,
  telefono      VARCHAR(32)  NULL,
  direccion     VARCHAR(200) NULL,
  fecha_nacimiento DATE      NULL,
  CONSTRAINT uk_usuario_perfil UNIQUE (usuario_id),
  CONSTRAINT fk_usuario_perfil_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
    ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE docente (
  id            BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  usuario_id    BIGINT UNSIGNED NOT NULL,
  programa_id   BIGINT UNSIGNED NOT NULL,
  especialidad  VARCHAR(160) NULL,
  fecha_ingreso DATE         NOT NULL,
  activo        TINYINT(1)   NOT NULL DEFAULT 1,
  CONSTRAINT uk_docente_usuario UNIQUE (usuario_id),
  CONSTRAINT fk_docente_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_docente_programa FOREIGN KEY (programa_id) REFERENCES programa(id)
    ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB;

-- =========================================
-- 3) TABLA ALUMNOS
-- =========================================
CREATE TABLE alumnos (
  id                 BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  nombres            VARCHAR(120) NOT NULL,
  apellidos          VARCHAR(120) NOT NULL,
  promedio           DECIMAL(4,2) NOT NULL DEFAULT 0.00,
  estado_codigo      VARCHAR(16)  NOT NULL DEFAULT 'ACTIVO',
  carrera            VARCHAR(16)  NOT NULL,
  usuario_id         BIGINT UNSIGNED NULL,
  asesor_docente_id  BIGINT UNSIGNED NULL,
  creado_en          DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  actualizado_en     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT ck_alumnos_promedio CHECK (promedio >= 0.00 AND promedio <= 5.00),
  CONSTRAINT fk_alumnos_carrera
    FOREIGN KEY (carrera) REFERENCES programa(codigo)
      ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT fk_alumnos_estado
    FOREIGN KEY (estado_codigo) REFERENCES estado_alumno(codigo)
      ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT fk_alumnos_usuario
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
      ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_alumnos_asesor
    FOREIGN KEY (asesor_docente_id) REFERENCES docente(id)
      ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT uk_alumnos_usuario UNIQUE (usuario_id)
) ENGINE=InnoDB;

CREATE INDEX idx_alumnos_carrera ON alumnos(carrera);
CREATE INDEX idx_alumnos_asesor ON alumnos(asesor_docente_id);
CREATE INDEX idx_alumnos_estado ON alumnos(estado_codigo);

CREATE TABLE alumno_estado_historial (
  id            BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  alumno_id     BIGINT UNSIGNED NOT NULL,
  estado_codigo VARCHAR(16)     NOT NULL,
  observacion   VARCHAR(200)    NULL,
  fecha_cambio  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_alumno_estado_historial_alumno
    FOREIGN KEY (alumno_id) REFERENCES alumnos(id)
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_alumno_estado_historial_estado
    FOREIGN KEY (estado_codigo) REFERENCES estado_alumno(codigo)
      ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB;

CREATE INDEX idx_alumno_estado_historial_alumno ON alumno_estado_historial(alumno_id);
CREATE INDEX idx_alumno_estado_historial_estado ON alumno_estado_historial(estado_codigo);

-- =========================================
-- 4) PERIODOS, ASIGNATURAS Y CURSOS
-- =========================================
CREATE TABLE periodo_academico (
  id           BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  codigo       VARCHAR(32)  NOT NULL UNIQUE,
  nombre       VARCHAR(120) NOT NULL,
  fecha_inicio DATE         NOT NULL,
  fecha_fin    DATE         NOT NULL
) ENGINE=InnoDB;

CREATE TABLE asignatura (
  id             BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  codigo         VARCHAR(32)  NOT NULL UNIQUE,
  nombre         VARCHAR(160) NOT NULL,
  programa       VARCHAR(16)  NOT NULL,
  creditos       TINYINT UNSIGNED NOT NULL DEFAULT 3,
  horas_semanales TINYINT UNSIGNED NOT NULL DEFAULT 4,
  CONSTRAINT fk_asignatura_programa FOREIGN KEY (programa) REFERENCES programa(codigo)
    ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB;

CREATE TABLE curso (
  id             BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  asignatura_id  BIGINT UNSIGNED NOT NULL,
  docente_id     BIGINT UNSIGNED NOT NULL,
  periodo_id     BIGINT UNSIGNED NOT NULL,
  cupo           SMALLINT UNSIGNED NOT NULL DEFAULT 30,
  aula           VARCHAR(32)  NULL,
  horario        VARCHAR(120) NULL,
  estado         ENUM('PLANIFICADO','PUBLICADO','CERRADO') NOT NULL DEFAULT 'PLANIFICADO',
  CONSTRAINT fk_curso_asignatura FOREIGN KEY (asignatura_id) REFERENCES asignatura(id)
    ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT fk_curso_docente FOREIGN KEY (docente_id) REFERENCES docente(id)
    ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT fk_curso_periodo FOREIGN KEY (periodo_id) REFERENCES periodo_academico(id)
    ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT uk_curso_asignatura_docente_periodo UNIQUE (asignatura_id, docente_id, periodo_id)
) ENGINE=InnoDB;

CREATE TABLE campus (
  id     BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  codigo VARCHAR(16)  NOT NULL UNIQUE,
  nombre VARCHAR(160) NOT NULL,
  direccion VARCHAR(200) NULL
) ENGINE=InnoDB;

CREATE TABLE aula (
  id        BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  campus_id BIGINT UNSIGNED NOT NULL,
  codigo    VARCHAR(16)  NOT NULL,
  capacidad SMALLINT UNSIGNED NOT NULL,
  tipo      ENUM('TEORICA','LABORATORIO','AUDITORIO') NOT NULL DEFAULT 'TEORICA',
  CONSTRAINT uk_aula UNIQUE (campus_id, codigo),
  CONSTRAINT fk_aula_campus FOREIGN KEY (campus_id) REFERENCES campus(id)
    ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE curso_sesion (
  id         BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  curso_id   BIGINT UNSIGNED NOT NULL,
  aula_id    BIGINT UNSIGNED NOT NULL,
  dia_semana ENUM('LUN','MAR','MIE','JUE','VIE','SAB') NOT NULL,
  hora_inicio TIME NOT NULL,
  hora_fin    TIME NOT NULL,
  CONSTRAINT fk_curso_sesion_curso FOREIGN KEY (curso_id) REFERENCES curso(id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_curso_sesion_aula FOREIGN KEY (aula_id) REFERENCES aula(id)
    ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB;

CREATE INDEX idx_curso_sesion_curso ON curso_sesion(curso_id);
CREATE INDEX idx_curso_sesion_aula ON curso_sesion(aula_id);

CREATE TABLE tipo_documento (
  id     BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  codigo VARCHAR(16)  NOT NULL UNIQUE,
  nombre VARCHAR(120) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE alumno_documento (
  id                BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  alumno_id         BIGINT UNSIGNED NOT NULL,
  tipo_documento_id BIGINT UNSIGNED NOT NULL,
  numero            VARCHAR(32)  NOT NULL,
  emisor            VARCHAR(120) NULL,
  fecha_emision     DATE         NULL,
  CONSTRAINT uk_alumno_documento UNIQUE (alumno_id, tipo_documento_id),
  CONSTRAINT fk_alumno_documento_alumno FOREIGN KEY (alumno_id) REFERENCES alumnos(id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_alumno_documento_tipo FOREIGN KEY (tipo_documento_id) REFERENCES tipo_documento(id)
    ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB;

INSERT INTO tipo_documento(codigo, nombre) VALUES
  ('DNI','Documento Nacional de Identidad'),
  ('PAS','Pasaporte'),
  ('CE','Carné de extranjería');

INSERT INTO campus(codigo, nombre, direccion) VALUES
  ('CENTRAL','Campus Central','Av. Principal 123'),
  ('NORTE','Campus Norte','Carretera 45 Km 2'),
  ('SUR','Campus Sur','Ruta Provincial 12');

INSERT INTO aula(campus_id, codigo, capacidad, tipo)
SELECT c.id, datos.codigo, datos.capacidad, datos.tipo
  FROM campus c
  JOIN (
        SELECT 'CENTRAL' campus_codigo, 'A101' codigo, 40 capacidad, 'TEORICA' tipo UNION ALL
        SELECT 'CENTRAL', 'L201', 25, 'LABORATORIO' UNION ALL
        SELECT 'NORTE', 'A102', 35, 'TEORICA' UNION ALL
        SELECT 'SUR', 'AUD1', 120, 'AUDITORIO'
       ) datos
    ON datos.campus_codigo = c.codigo;

CREATE TABLE programa_coordinador (
  programa_id BIGINT UNSIGNED NOT NULL,
  docente_id  BIGINT UNSIGNED NOT NULL,
  fecha_inicio DATE NOT NULL,
  fecha_fin    DATE NULL,
  PRIMARY KEY (programa_id, docente_id, fecha_inicio),
  CONSTRAINT fk_programa_coordinador_programa FOREIGN KEY (programa_id) REFERENCES programa(id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_programa_coordinador_docente FOREIGN KEY (docente_id) REFERENCES docente(id)
    ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;
-- =========================================
-- 5) MATRÍCULAS Y NOTAS
-- =========================================
CREATE TABLE matricula (
  id             BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  alumno_id      BIGINT UNSIGNED NOT NULL,
  curso_id       BIGINT UNSIGNED NOT NULL,
  fecha_registro DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
  estado         ENUM('INSCRITO','RETIRADO','APROBADO','REPROBADO') NOT NULL DEFAULT 'INSCRITO',
  CONSTRAINT fk_matricula_alumno FOREIGN KEY (alumno_id) REFERENCES alumnos(id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_matricula_curso FOREIGN KEY (curso_id) REFERENCES curso(id)
    ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT uk_matricula UNIQUE (alumno_id, curso_id)
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
CREATE INDEX idx_matricula_curso ON matricula(curso_id);
CREATE INDEX idx_nota_matricula ON nota(matricula_id);

-- =========================================
-- 6) AUDITORÍA
-- =========================================
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

CREATE TRIGGER trg_alumnos_estado_insert
AFTER INSERT ON alumnos
FOR EACH ROW
BEGIN
  INSERT INTO alumno_estado_historial(alumno_id, estado_codigo, observacion)
  VALUES (NEW.id, NEW.estado_codigo, 'Estado inicial');
END $$

CREATE TRIGGER trg_alumnos_estado_update
AFTER UPDATE ON alumnos
FOR EACH ROW
BEGIN
  IF NEW.estado_codigo <> OLD.estado_codigo THEN
    INSERT INTO alumno_estado_historial(alumno_id, estado_codigo, observacion)
    VALUES (NEW.id, NEW.estado_codigo,
            CONCAT('Cambio de estado desde ', OLD.estado_codigo, ' a ', NEW.estado_codigo));
  END IF;
END $$

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
          JSON_OBJECT('nombres', NEW.nombres, 'apellidos', NEW.apellidos, 'promedio', NEW.promedio, 'estado', NEW.estado_codigo, 'carrera', NEW.carrera, 'usuario_id', NEW.usuario_id));
END $$

CREATE TRIGGER trg_alumnos_update
AFTER UPDATE ON alumnos
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_cambios(usuario, tabla_afectada, accion, registro_id, valores_anteriores, valores_nuevos)
  VALUES (CURRENT_USER(), 'alumnos', 'UPDATE', NEW.id,
          JSON_OBJECT('nombres', OLD.nombres, 'apellidos', OLD.apellidos, 'promedio', OLD.promedio, 'estado', OLD.estado_codigo, 'carrera', OLD.carrera, 'usuario_id', OLD.usuario_id),
          JSON_OBJECT('nombres', NEW.nombres, 'apellidos', NEW.apellidos, 'promedio', NEW.promedio, 'estado', NEW.estado_codigo, 'carrera', NEW.carrera, 'usuario_id', NEW.usuario_id));
END $$

CREATE TRIGGER trg_alumnos_delete
AFTER DELETE ON alumnos
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_cambios(usuario, tabla_afectada, accion, registro_id, valores_anteriores, valores_nuevos)
  VALUES (CURRENT_USER(), 'alumnos', 'DELETE', OLD.id,
          JSON_OBJECT('nombres', OLD.nombres, 'apellidos', OLD.apellidos, 'promedio', OLD.promedio, 'estado', OLD.estado_codigo, 'carrera', OLD.carrera, 'usuario_id', OLD.usuario_id), NULL);
END $$

DELIMITER ;

-- =========================================
-- 7) USUARIOS SEMILLA
-- =========================================
-- Contraseña 'admin123' cifrada con bcrypt (puedes cambiarla cuando gustes)
INSERT INTO usuarios (ingresoUsuario, ingresoContrasenia, tipo_de_usuario)
VALUES (
  'admin',
  '$2y$10$E9nPo.Eys2SbkDXOiv1sEuLT4Va2eG5n0UZANUeAFX3K8t1Bz88.i', -- admin123
  'ADMIN'
);

INSERT INTO usuario_perfil (usuario_id, nombres, apellidos, correo)
SELECT u.id, 'Administrador', 'Principal', 'admin@example.com'
  FROM usuarios u
 WHERE u.ingresoUsuario = 'admin';

-- =========================================
-- LISTO
-- =========================================
SELECT '✅ Base de datos LOGIN creada correctamente con usuario ADMIN listo para usar.' AS resultado;
