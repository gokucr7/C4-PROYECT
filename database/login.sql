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
