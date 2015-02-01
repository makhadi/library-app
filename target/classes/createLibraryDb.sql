CREATE SCHEMA `library`;

CREATE TABLE `library`.`librarianinfo` (
  `librarianId` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `emailId` VARCHAR(45) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `status` VARCHAR(45) NULL DEFAULT 'Active',
  `lastUpdatedBy` INT NULL DEFAULT 1000,
  `lastUpdatedDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`librarianId`),
  UNIQUE INDEX `emailId_UNIQUE` (`emailId` ASC));

CREATE TABLE `library`.`studentinfo` (
  `studentId` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `phoneNumber` VARCHAR(45) NULL,
  `status` VARCHAR(45) NULL DEFAULT 'Active',
  `lastUpdatedBy` INT NULL DEFAULT 1000,
  `lastUpdatedDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`studentId`));

CREATE TABLE `library`.`bookinfo` (
  `bookId` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `publisher` VARCHAR(45) NULL,
  `author` VARCHAR(45) NULL,
  `price` FLOAT NULL,
  `numOfBooks` INT NULL DEFAULT 0,
  `status` VARCHAR(45) NULL DEFAULT 'Active',
  `lastUpdatedBy` INT NULL DEFAULT 1000,
  `lastUpdatedDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`bookId`));

CREATE TABLE `library`.`bookissue` (
  `bookId` INT NOT NULL,
  `studentId` INT NOT NULL,
  `dateIssued` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `dueDate` DATE NOT NULL,
  `status` VARCHAR(45) NULL DEFAULT 'Active',
  `lastUpdatedBy` INT NULL DEFAULT 1000,
  `lastUpdatedDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`bookId`, `studentId`),
  INDEX `fk_studendId_idx` (`studentId` ASC),
  CONSTRAINT `fk_bookId`
    FOREIGN KEY (`bookId`)
    REFERENCES `library`.`bookinfo` (`bookId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_studendId`
    FOREIGN KEY (`studentId`)
    REFERENCES `library`.`studentinfo` (`studentId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
    insert into bookinfo (title, publisher, author, price, numOfBooks) values("Advanced Java","Pearson","BertBates",99,15);
    
    insert into librarianinfo (firstName, lastName, emailId, password) values("John","Smith","demouser@test.com","fremont");
    
    insert into studentinfo (firstName, lastName, phoneNumber) values ("Bob", "Wilson", "408-408-4084");