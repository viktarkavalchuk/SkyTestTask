-- MySQL Script generated by MySQL Workbench
-- Thu Sep 29 15:00:48 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema SkytecGames
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `SkytecGames` ;

-- -----------------------------------------------------
-- Schema SkytecGames
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SkytecGames` DEFAULT CHARACTER SET utf8 ;
USE `SkytecGames` ;

-- -----------------------------------------------------
-- Table `SkytecGames`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SkytecGames`.`User` (
  `idUser` BIGINT(10) NOT NULL,
  `name` VARCHAR(80) NULL,
  `userGold` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`idUser`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SkytecGames`.`Clan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SkytecGames`.`Clan` (
  `idClan` BIGINT(10) NOT NULL,
  `name` VARCHAR(80) NULL,
  `gold` INT NULL,
  PRIMARY KEY (`idClan`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SkytecGames`.`ClansToUser`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SkytecGames`.`ClansToUser` (
  `idClansToUser` BIGINT(10) NOT NULL,
  `idClan` BIGINT(10) NULL,
  `idUser` BIGINT(10) NULL,
  PRIMARY KEY (`idClansToUser`),
  INDEX `ClanToUser_idx` (`idClan` ASC) VISIBLE,
  INDEX `UserToClan_idx` (`idUser` ASC) VISIBLE,
  CONSTRAINT `ClanToUser`
    FOREIGN KEY (`idClan`)
    REFERENCES `SkytecGames`.`Clan` (`idClan`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `UserToClan`
    FOREIGN KEY (`idUser`)
    REFERENCES `SkytecGames`.`User` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SkytecGames`.`TransactionHistory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SkytecGames`.`TransactionHistory` (
  `idTransactionHistory` BIGINT(10) NOT NULL,
  `date` DATE NULL,
  `user` BIGINT(10) NULL,
  `clan` BIGINT(10) NULL,
  `goldInTransaction` INT NULL,
  INDEX `userName_idx` (`user` ASC) VISIBLE,
  INDEX `clanName_idx` (`clan` ASC) VISIBLE,
  CONSTRAINT `userName`
    FOREIGN KEY (`user`)
    REFERENCES `SkytecGames`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `clanName`
    FOREIGN KEY (`clan`)
    REFERENCES `SkytecGames`.`Clan` (`idClan`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SkytecGames`.`Task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SkytecGames`.`Task` (
  `idTask` BIGINT(10) NOT NULL,
  `taskName` VARCHAR(255) NULL,
  `price` INT NULL,
  PRIMARY KEY (`idTask`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `SkytecGames`.`User`
-- -----------------------------------------------------
START TRANSACTION;
USE `SkytecGames`;
INSERT INTO `SkytecGames`.`User` (`idUser`, `name`, `userGold`) VALUES (1, 'Bilbo Baggins', 10);
INSERT INTO `SkytecGames`.`User` (`idUser`, `name`, `userGold`) VALUES (2, 'The Old Took', 500);
INSERT INTO `SkytecGames`.`User` (`idUser`, `name`, `userGold`) VALUES (3, 'Bard the Bowman', 5700);
INSERT INTO `SkytecGames`.`User` (`idUser`, `name`, `userGold`) VALUES (4, 'Beorn', 200);
INSERT INTO `SkytecGames`.`User` (`idUser`, `name`, `userGold`) VALUES (5, 'Thror', 245);
INSERT INTO `SkytecGames`.`User` (`idUser`, `name`, `userGold`) VALUES (6, 'Thrain', 688);
INSERT INTO `SkytecGames`.`User` (`idUser`, `name`, `userGold`) VALUES (7, 'Dain', 125);
INSERT INTO `SkytecGames`.`User` (`idUser`, `name`, `userGold`) VALUES (8, 'Thorin Oakenshield', 0);
INSERT INTO `SkytecGames`.`User` (`idUser`, `name`, `userGold`) VALUES (9, 'Elrond', 9000);
INSERT INTO `SkytecGames`.`User` (`idUser`, `name`, `userGold`) VALUES (10, 'Galion', 850);

COMMIT;


-- -----------------------------------------------------
-- Data for table `SkytecGames`.`Clan`
-- -----------------------------------------------------
START TRANSACTION;
USE `SkytecGames`;
INSERT INTO `SkytecGames`.`Clan` (`idClan`, `name`, `gold`) VALUES (1, 'Elf clan', 1500);
INSERT INTO `SkytecGames`.`Clan` (`idClan`, `name`, `gold`) VALUES (2, 'Dwarf clan', 5000);
INSERT INTO `SkytecGames`.`Clan` (`idClan`, `name`, `gold`) VALUES (3, 'Human clan', 100);
INSERT INTO `SkytecGames`.`Clan` (`idClan`, `name`, `gold`) VALUES (4, 'Hobbit clan', 600);

COMMIT;


-- -----------------------------------------------------
-- Data for table `SkytecGames`.`ClansToUser`
-- -----------------------------------------------------
START TRANSACTION;
USE `SkytecGames`;
INSERT INTO `SkytecGames`.`ClansToUser` (`idClansToUser`, `idClan`, `idUser`) VALUES (1, 4, 1);
INSERT INTO `SkytecGames`.`ClansToUser` (`idClansToUser`, `idClan`, `idUser`) VALUES (2, 4, 2);
INSERT INTO `SkytecGames`.`ClansToUser` (`idClansToUser`, `idClan`, `idUser`) VALUES (3, 3, 3);
INSERT INTO `SkytecGames`.`ClansToUser` (`idClansToUser`, `idClan`, `idUser`) VALUES (4, 3, 4);
INSERT INTO `SkytecGames`.`ClansToUser` (`idClansToUser`, `idClan`, `idUser`) VALUES (5, 2, 5);
INSERT INTO `SkytecGames`.`ClansToUser` (`idClansToUser`, `idClan`, `idUser`) VALUES (6, 2, 6);
INSERT INTO `SkytecGames`.`ClansToUser` (`idClansToUser`, `idClan`, `idUser`) VALUES (7, 2, 7);
INSERT INTO `SkytecGames`.`ClansToUser` (`idClansToUser`, `idClan`, `idUser`) VALUES (8, 2, 8);
INSERT INTO `SkytecGames`.`ClansToUser` (`idClansToUser`, `idClan`, `idUser`) VALUES (9, 1, 9);
INSERT INTO `SkytecGames`.`ClansToUser` (`idClansToUser`, `idClan`, `idUser`) VALUES (10, 1, 10);

COMMIT;


-- -----------------------------------------------------
-- Data for table `SkytecGames`.`Task`
-- -----------------------------------------------------
START TRANSACTION;
USE `SkytecGames`;
INSERT INTO `SkytecGames`.`Task` (`idTask`, `taskName`, `price`) VALUES (1, 'Kill the dragon', 5000);
INSERT INTO `SkytecGames`.`Task` (`idTask`, `taskName`, `price`) VALUES (2, 'Take the ring to Mordor', 10000);
INSERT INTO `SkytecGames`.`Task` (`idTask`, `taskName`, `price`) VALUES (3, 'Find out what\'s going on in Dol Guldur', 500);
INSERT INTO `SkytecGames`.`Task` (`idTask`, `taskName`, `price`) VALUES (4, 'Protect Gondor from the Haradrim', 1000);

COMMIT;

