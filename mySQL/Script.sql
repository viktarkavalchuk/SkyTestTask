-- MySQL Script generated by MySQL Workbench
-- Fri Sep 30 18:05:57 2022
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
  `idUser` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(80) NULL,
  `userGold` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`idUser`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SkytecGames`.`Clan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SkytecGames`.`Clan` (
  `idClan` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(80) NULL,
  `gold` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`idClan`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SkytecGames`.`ClansToUser`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SkytecGames`.`ClansToUser` (
  `idClansToUser` BIGINT NOT NULL AUTO_INCREMENT,
  `idClan` BIGINT NULL,
  `idUser` BIGINT NULL,
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
-- Table `SkytecGames`.`Task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SkytecGames`.`Task` (
  `idTask` BIGINT NOT NULL AUTO_INCREMENT,
  `taskName` VARCHAR(255) NULL,
  `price` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`idTask`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SkytecGames`.`Reason`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SkytecGames`.`Reason` (
  `idReason` BIGINT NOT NULL,
  `reason` VARCHAR(255) NULL,
  PRIMARY KEY (`idReason`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SkytecGames`.`TransactionHistory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SkytecGames`.`TransactionHistory` (
  `idTransactionHistory` BIGINT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NULL,
  `userId` BIGINT NULL,
  `taskId` BIGINT NULL,
  `clanId` BIGINT NULL,
  `goldBefore` INT NULL,
  `goldAfter` INT NULL,
  `reason` BIGINT NULL,
  PRIMARY KEY (`idTransactionHistory`),
  INDEX `userId_idx` (`userId` ASC) VISIBLE,
  INDEX `clansId_idx` (`clanId` ASC) VISIBLE,
  INDEX `tasksId_idx` (`taskId` ASC) VISIBLE,
  INDEX `reasonId_idx` (`reason` ASC) VISIBLE,
  CONSTRAINT `usersId`
    FOREIGN KEY (`userId`)
    REFERENCES `SkytecGames`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `clansId`
    FOREIGN KEY (`clanId`)
    REFERENCES `SkytecGames`.`Clan` (`idClan`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `tasksId`
    FOREIGN KEY (`taskId`)
    REFERENCES `SkytecGames`.`Task` (`idTask`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `reasonId`
    FOREIGN KEY (`reason`)
    REFERENCES `SkytecGames`.`Reason` (`idReason`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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


-- -----------------------------------------------------
-- Data for table `SkytecGames`.`Reason`
-- -----------------------------------------------------
START TRANSACTION;
USE `SkytecGames`;
INSERT INTO `SkytecGames`.`Reason` (`idReason`, `reason`) VALUES (1, 'Replenishment of the treasury from the user');
INSERT INTO `SkytecGames`.`Reason` (`idReason`, `reason`) VALUES (2, 'Replenishment of the treasury when completing the quest');
INSERT INTO `SkytecGames`.`Reason` (`idReason`, `reason`) VALUES (3, 'For the needs of the clan');

COMMIT;


-- -----------------------------------------------------
-- Data for table `SkytecGames`.`TransactionHistory`
-- -----------------------------------------------------
START TRANSACTION;
USE `SkytecGames`;
INSERT INTO `SkytecGames`.`TransactionHistory` (`idTransactionHistory`, `date`, `userId`, `taskId`, `clanId`, `goldBefore`, `goldAfter`, `reason`) VALUES (1, '2022-01-20', 1, NULL, 4, 150, 250, 1);
INSERT INTO `SkytecGames`.`TransactionHistory` (`idTransactionHistory`, `date`, `userId`, `taskId`, `clanId`, `goldBefore`, `goldAfter`, `reason`) VALUES (2, '2022-01-20', 2, NULL, 4, 5000, 12000, 2);
INSERT INTO `SkytecGames`.`TransactionHistory` (`idTransactionHistory`, `date`, `userId`, `taskId`, `clanId`, `goldBefore`, `goldAfter`, `reason`) VALUES (3, '2022-01-20', 3, NULL, 3, 500, 7500, 1);
INSERT INTO `SkytecGames`.`TransactionHistory` (`idTransactionHistory`, `date`, `userId`, `taskId`, `clanId`, `goldBefore`, `goldAfter`, `reason`) VALUES (4, '2022-01-20', NULL, 2, 1, 200, 201, 1);

COMMIT;

