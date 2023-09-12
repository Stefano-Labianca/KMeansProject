CREATE DATABASE MapDB;

USE MapDB;

CREATE TABLE playtennis(
     outlook varchar(10),
     temperature float(5,2),
     umidity varchar(10),
     wind varchar(10),
     play varchar(10)
);


insert into playtennis values
('sunny',30.3,'high','weak','no'),
('sunny',30.3,'high','strong','no'),
('overcast',30.0,'high','weak','yes'),
('rain',13.0,'high','weak','yes'),
('rain',0.0,'normal','weak','yes'),
('rain',0.0,'normal','strong','no'),
('overcast',0.1,'normal','strong','yes'),
('sunny',13.0,'high','weak','no'),
('sunny',0.1,'normal','weak','yes'),
('rain',12.0,'normal','weak','yes'),
('sunny',12.5,'normal','strong','yes'),
('overcast',12.5,'high','strong','yes'),
('overcast',29.21,'normal','weak','yes'),
('rain',12.5,'high','strong','no');