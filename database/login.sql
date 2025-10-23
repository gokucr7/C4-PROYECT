-- =========================================
-- BASE DE DATOS LOGIN - ESQUEMA LIMPIO
-- =========================================

DROP SCHEMA IF EXISTS login;
CREATE SCHEMA login
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_0900_ai_ci;
USE login;

SET SESSION sql_mode = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- =========================================
-- CATÁLOGOS BÁSICOS
-- =========================================

CREATE TABLE rol (
  id     BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  codigo VARCHAR(32)  NOT NULL UNIQUE,
  nombre VARCHAR(64)  NOT NULL
) ENGINE=InnoDB;

INSERT INTO rol(codigo, nombre) VALUES
  ('ADMIN', 'Administrador'),
  ('DOCENTE', 'Docente'),
  ('ESTUDIANTE', 'Estudiante');

CREATE TABLE programa (
  id     BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  codigo VARCHAR(16)  NOT NULL UNIQUE,
  nombre VARCHAR(120) NOT NULL
) ENGINE=InnoDB;

INSERT INTO programa(codigo, nombre) VALUES
  ('SIS', 'Ingeniería de Sistemas'),
  ('ADM', 'Administración de Empresas'),
  ('IND', 'Ingeniería Industrial'),
  ('ELE', 'Ingeniería Electrónica'),
  ('AGR', 'Agronomía');

-- =========================================
-- USUARIOS Y SEGURIDAD
-- =========================================

CREATE TABLE usuarios (
  id                 BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  ingresoUsuario     VARCHAR(120) NOT NULL UNIQUE,
  ingresoContrasenia VARCHAR(255) NOT NULL,
  tipo_de_usuario    VARCHAR(32)  NOT NULL,
  activo             TINYINT(1)   NOT NULL DEFAULT 1,
  ultimo_acceso      DATETIME     NULL,
  creado_en          DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  actualizado_en     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT fk_usuarios_rol FOREIGN KEY (tipo_de_usuario) REFERENCES rol(codigo)
    ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB;

CREATE INDEX idx_usuarios_tipo_de_usuario ON usuarios(tipo_de_usuario);

INSERT INTO usuarios(ingresoUsuario, ingresoContrasenia, tipo_de_usuario)
VALUES (
  'admin',
  '$2y$10$E9nPo.Eys2SbkDXOiv1sEuLT4Va2eG5n0UZANUeAFX3K8t1Bz88.i', -- admin123
  'ADMIN'
);

-- =========================================
-- ALUMNOS
-- =========================================

CREATE TABLE alumnos (
  id        BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  nombres   VARCHAR(120) NOT NULL,
  apellidos VARCHAR(120) NOT NULL,
  promedio  DECIMAL(4,2) NOT NULL DEFAULT 0.00,
  carrera   VARCHAR(16)  NOT NULL,
  creado_en DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  actualizado_en DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT ck_alumnos_promedio CHECK (promedio BETWEEN 0.00 AND 5.00),
  CONSTRAINT fk_alumnos_programa FOREIGN KEY (carrera) REFERENCES programa(codigo)
    ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB;

CREATE INDEX idx_alumnos_carrera ON alumnos(carrera);

INSERT INTO alumnos(nombres, apellidos, promedio, carrera) VALUES
  ('Juana', 'Pérez Ríos', 4.50, 'SIS'),
  ('Marco', 'López Díaz', 3.20, 'ADM');

-- =========================================
-- CONFIRMACIÓN
-- =========================================

SELECT '✅ Base de datos LOGIN inicializada correctamente.' AS resultado;
