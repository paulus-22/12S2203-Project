-- proyek database pbo

CREATE DATABASE ParkSytemDB
ON
  PRIMARY (NAME = ParkSytemDBPrimary,
  FILENAME ='E:\Materi kuliah\Semester 4\PBO\Database.mdf', 
  SIZE = 30MB,
  MAXSIZE = 200MB,
  FILEGROWTH = 10% )
  LOG ON  
  (NAME = ParkSytemDBLog, 
  FILENAME ='E:\Materi kuliah\Semester 4\PBO\Database.ldf',
  SIZE = 15MB,
  MAXSIZE = 150MB,
  FILEGROWTH = 15%)

CREATE TABLE ParkSlot (
	SlotNo CHAR(3) NOT NULL,
	StatusID INT ,
	StatusSlot CHAR(12) NOT NULL,
	PRIMARY KEY (SlotNo)
)

CREATE TABLE CardPark (
	CardID CHAR(8) NOT NULL,
	CardTimeIn TIME,
	CardTimeOut TIME, 
	CardFee INT NOT NULL,	
	StatusCard CHAR(7) NOT NULL, 
	PRIMARY KEY (CardID)
) 

CREATE TABLE Customer (
	PlateNo CHAR(10) NOT NULL,
	CardID CHAR(8) NOT NULL,
	SlotNo CHAR(3) NOT NULL,
	StatusPlate CHAR(7) NOT NULL,
	PRIMARY KEY (plateNo),
	FOREIGN KEY (CardID) REFERENCES CardPark (CardId),
	FOREIGN KEY (SlotNo) REFERENCES ParkSlot (SlotNo)
)

CREATE TABLE TransactionPark (
	SlipNo INT NOT NULL,
	PlateNo CHAR(10) NOT NULL,
	CardID CHAR(8) NOT NULL,
	SlotNo CHAR(3) NOT NULL,
	StatusSlipNo CHAR(7) NOT NULL,
	ColourLight CHAR(5) NOT NULL,
	PRIMARY KEY (SlipNo),
	FOREIGN KEY (PlateNo) REFERENCES Customer (PlateNo),
	FOREIGN KEY (CardID) REFERENCES CardPark (CardId),
	FOREIGN KEY (SlotNo) REFERENCES ParkSlot (SlotNo)
)

--Insert Table ParkSlot
INSERT INTO ParkSlot Values ('F05', 1, 'Available');
INSERT INTO ParkSlot Values ('A03', 0, 'NotAvailable');
INSERT INTO ParkSlot Values ('G07', 1, 'Available');

SELECT * FROM ParkSlot 

--Insert Table CardPark
INSERT INTO CardPark Values ('DJOK0001', '12:01:45', '12:40:05', 0, 'Free');
INSERT INTO CardPark Values ('DJOK0005', '10:03:00', '11:35:00', 20000, 'NotFree');
INSERT INTO CardPark Values ('DJOK0007', '15:00:00', '15:40:13', 0, 'Free');

SELECT * FROM CardPark 

--Insert Table Customer
INSERT INTO Customer Values ('BK1234US', 'DJOK0007', 'G07', 'GetIn');
INSERT INTO Customer Values ('BK9876UTS', 'DJOK0005', 'A03', 'GetOut');
INSERT INTO Customer Values ('BK1506UAS', 'DJOK0001', 'F05', 'GetIn');

SELECT * FROM Customer 

--Insert Table TransactionPark
INSERT INTO TransactionPark Values (5,'BK1234US', 'DJOK0007', 'G07' , 'Nothing', 'Red');
INSERT INTO TransactionPark Values (2,'BK9876UTS', 'DJOK0005', 'A03' , 'Succed', 'Green');
INSERT INTO TransactionPark Values (8,'BK1506UAS', 'DJOK0001', 'F05' , 'Nothing', 'Red');

SELECT * FROM TransactionPark 
