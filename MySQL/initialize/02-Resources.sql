CREATE DATABASE IF NOT EXISTS resources;

CREATE USER IF NOT EXISTS 'resources' IDENTIFIED BY 'password';

GRANT ALL PRIVILEGES ON resources.* TO 'resources';
