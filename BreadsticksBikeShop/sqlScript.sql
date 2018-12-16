DROP TABLE Client;
DROP TABLE Part;
DROP TABLE Invoice;
DROP TABLE InvoiceLine;

CREATE TABLE Client (clientID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, firstName VARCHAR(25), lastName VARCHAR(50), address VARCHAR(50), phone VARCHAR(15));

INSERT INTO Client (firstName, lastName, address, phone) VALUES ('Jessica', 'Jones', '947 This Ave', '(323) 555-0351');
INSERT INTO Client (firstName, lastName, address, phone) VALUES ('Wall', 'E', '411 Earth Lane', '(626) 555-0821');
INSERT INTO Client (firstName, lastName, address, phone) VALUES ('Pongo', 'Fernandez', '611 Tennessee Rd', '(818) 555-3120');
INSERT INTO Client (firstName, lastName, address, phone) VALUES ('Alex', 'Ramirez', '123 Hearthstone Ave', '(415) 565-7931');
INSERT INTO Client (firstName, lastName, address, phone) VALUES ('Aaron', 'Cervantes', '445 Huffle St', '(619) 225-8301');
INSERT INTO Client (firstName, lastName, address, phone) VALUES ('Kathleen', 'Hang', '777 Epic Blvd', '(707) 995-8221');
INSERT INTO Client (firstName, lastName, address, phone) VALUES ('Ketchup', 'Tomato', '331 Curly Rd', '(339) 765-8231');
INSERT INTO Client (firstName, lastName, address, phone) VALUES ('Sohair', 'Zaki', '1713 Chino Dr', '(444) 132-8463');

CREATE TABLE Invoice    (invoiceID INT NOT NULL AUTO_INCREMENT, 
                        clientID INT NOT NULL,
                        dateIn VARCHAR(15) NOT NULL,
                        dateOut VARCHAR(15) NOT NULL,
                        PRIMARY KEY(invoiceID),
                        FOREIGN KEY(clientID) REFERENCES Client(clientID)
                            ON DELETE CASCADE
                            ON UPDATE CASCADE);

CREATE TABLE Part       (partID INT NOT NULL AUTO_INCREMENT,
                        name varchar(25) NOT NULL,
                        price DOUBLE NOT NULL,
                        description VARCHAR(45),
                        PRIMARY KEY(partID));

INSERT INTO Part (name, price, description) VALUES ('Brakes', '16', 'Premium');
INSERT INTO Part (name, price, description) VALUES ('Engine', '73', '2-Stroke');
INSERT INTO Part (name, price, description) VALUES ('Handlebars', '50', 'Steel');
INSERT INTO Part (name, price, description) VALUES ('Saddle', '150', 'Premium Leather');
INSERT INTO Part (name, price, description) VALUES ('Tires', '320', 'Heavy Duty');
INSERT INTO Part (name, price, description) VALUES ('Frame', '110', 'Aluminum Alloy');
INSERT INTO Part (name, price, description) VALUES ('Frame', '800', 'Titanium');


CREATE TABLE InvoiceLine (invoiceLineID INT NOT NULL,
                         invoiceID INT NOT NULL,
                         partID INT NOT NULL,
                         quantity INT NOT NULL,
                         PRIMARY KEY (invoiceLineID, invoiceID),
                         FOREIGN KEY (invoiceID) REFERENCES Invoice(invoiceID)
                            ON DELETE CASCADE
                            ON UPDATE CASCADE,
                         FOREIGN KEY (partID) REFERENCES Part(partID)
                            ON DELETE CASCADE
                            ON UPDATE CASCADE);