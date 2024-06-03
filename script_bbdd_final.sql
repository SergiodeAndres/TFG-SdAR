-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema proyectodb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema proyectodb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `proyectodb` ;
USE `proyectodb` ;

-- -----------------------------------------------------
-- Table `proyectodb`.`sitio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectodb`.`sitio` (
  `sitioID` INT NOT NULL AUTO_INCREMENT,
  `calle` VARCHAR(255) COLLATE 'utf8mb3_spanish_ci' NOT NULL,
  `numero` VARCHAR(255) COLLATE 'utf8mb3_spanish_ci' NOT NULL,
  `codigoPostal` VARCHAR(255) COLLATE 'utf8mb3_spanish_ci' NOT NULL,
  `ciudad` VARCHAR(255) COLLATE 'utf8mb3_spanish_ci' NOT NULL,
  `provincia` VARCHAR(255) COLLATE 'utf8mb3_spanish_ci' NOT NULL,
  PRIMARY KEY (`sitioID`))
ENGINE = InnoDB
AUTO_INCREMENT = 7;


-- -----------------------------------------------------
-- Table `proyectodb`.`atraccion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectodb`.`atraccion` (
  `atraccionID` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) COLLATE 'utf8mb3_spanish_ci' NOT NULL,
  `capacidad` INT NOT NULL,
  `sitioID` INT NOT NULL,
  `precio` FLOAT NOT NULL,
  PRIMARY KEY (`atraccionID`),
  INDEX `fk_atraccion_sitio_idx` (`sitioID` ASC) VISIBLE,
  CONSTRAINT `fk_atraccion_sitio`
    FOREIGN KEY (`sitioID`)
    REFERENCES `proyectodb`.`sitio` (`sitioID`))
ENGINE = InnoDB
AUTO_INCREMENT = 9;


-- -----------------------------------------------------
-- Table `proyectodb`.`reserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectodb`.`reserva` (
  `reservaID` INT NOT NULL,
  `nombreContacto` VARCHAR(255) COLLATE 'utf8mb3_spanish_ci' NOT NULL,
  `telefonoContacto` VARCHAR(255) COLLATE 'utf8mb3_spanish_ci' NOT NULL,
  `emailContacto` VARCHAR(255) COLLATE 'utf8mb3_spanish_ci' NOT NULL,
  `fechaReserva` DATE NOT NULL,
  `datosPago` VARCHAR(255) COLLATE 'utf8mb3_spanish_ci' NOT NULL,
  `personas` INT NOT NULL,
  `sitioID` INT NOT NULL,
  PRIMARY KEY (`reservaID`),
  INDEX `fk_atraccion_sitio_idx` (`sitioID` ASC) VISIBLE,
  CONSTRAINT `fk_reserva_sitio`
    FOREIGN KEY (`sitioID`)
    REFERENCES `proyectodb`.`sitio` (`sitioID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyectodb`.`atraccion_reserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectodb`.`atraccion_reserva` (
  `atraccionID` INT NOT NULL,
  `reservaID` INT NOT NULL,
  `sesion` TIME NOT NULL,
  PRIMARY KEY (`atraccionID`, `reservaID`, `sesion`),
  INDEX `fk_atr_res_res_idx` (`reservaID` ASC) VISIBLE,
  CONSTRAINT `fk_atr_res_atraccion`
    FOREIGN KEY (`atraccionID`)
    REFERENCES `proyectodb`.`atraccion` (`atraccionID`),
  CONSTRAINT `fk_atr_res_res`
    FOREIGN KEY (`reservaID`)
    REFERENCES `proyectodb`.`reserva` (`reservaID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyectodb`.`empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectodb`.`empleado` (
  `DNI` VARCHAR(9) COLLATE 'utf8mb3_spanish_ci' NOT NULL DEFAULT '00000000A',
  `nombre` VARCHAR(255) COLLATE 'utf8mb3_spanish_ci' NOT NULL,
  `apellido` VARCHAR(255) COLLATE 'utf8mb3_spanish_ci' NOT NULL,
  `email` VARCHAR(255) COLLATE 'utf8mb3_spanish_ci' NOT NULL,
  `password` VARCHAR(255) COLLATE 'utf8mb3_spanish_ci' NOT NULL,
  `sitioID` INT NOT NULL,
  `gerente` TINYINT NOT NULL,
  PRIMARY KEY (`DNI`),
  INDEX `fk_empleado_sitio_idx` (`sitioID` ASC) VISIBLE,
  CONSTRAINT `fk_empleado_sitio`
    FOREIGN KEY (`sitioID`)
    REFERENCES `proyectodb`.`sitio` (`sitioID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyectodb`.`incidencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectodb`.`incidencia` (
  `incidenciaID` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(2500) COLLATE 'utf8mb3_spanish_ci' NOT NULL,
  `cerrada` TINYINT NOT NULL,
  `DNI_empleado` VARCHAR(9) COLLATE 'utf8mb3_spanish_ci' NOT NULL,
  PRIMARY KEY (`incidenciaID`),
  INDEX `fk_incidencia_empleado` (`DNI_empleado` ASC) VISIBLE,
  CONSTRAINT `fk_incidencia_empleado`
    FOREIGN KEY (`DNI_empleado`)
    REFERENCES `proyectodb`.`empleado` (`DNI`))
ENGINE = InnoDB
AUTO_INCREMENT = 8;


-- -----------------------------------------------------
-- Table `proyectodb`.`miembroclub`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectodb`.`miembroclub` (
  `email` VARCHAR(255) COLLATE 'utf8mb3_spanish_ci' NOT NULL DEFAULT 'default@default.com',
  PRIMARY KEY (`email`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyectodb`.`tarjeta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectodb`.`tarjeta` (
  `numeroID` VARCHAR(255) COLLATE 'utf8mb3_spanish_ci' NOT NULL DEFAULT '000000000',
  `pin` VARCHAR(255) COLLATE 'utf8mb3_spanish_ci' NOT NULL,
  `saldoMoneda` FLOAT NOT NULL,
  `saldoTickets` INT NOT NULL,
  PRIMARY KEY (`numeroID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyectodb`.`turno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectodb`.`turno` (
  `turnoId` INT NOT NULL AUTO_INCREMENT,
  `sitio` INT NOT NULL,
  `empleado` VARCHAR(9) COLLATE 'utf8mb3_spanish_ci' NOT NULL,
  `fecha` DATE NOT NULL,
  `horaEntrada` TIME NOT NULL,
  `horaSalida` TIME NOT NULL,
  PRIMARY KEY (`turnoId`),
  INDEX `fk_turno_sitio_idx` (`sitio` ASC) VISIBLE,
  INDEX `fk_turno_empleado` (`empleado` ASC) VISIBLE,
  CONSTRAINT `fk_turno_empleado`
    FOREIGN KEY (`empleado`)
    REFERENCES `proyectodb`.`empleado` (`DNI`),
  CONSTRAINT `fk_turno_sitio`
    FOREIGN KEY (`sitio`)
    REFERENCES `proyectodb`.`sitio` (`sitioID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
AUTO_INCREMENT = 6;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
