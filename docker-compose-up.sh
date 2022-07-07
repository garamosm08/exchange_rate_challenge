#!/bin/sh

#Constantes de variables para script
BLUE_COLOR='\033[0;34m'
NO_COLOR='\033[0m'

#Compilacion y creacion de package de component
echo -e "${BLUE_COLOR}Compilacion y creacion de package de component: exchange rate${NO_COLOR}"

mvn clean package -DskipTests=true -f ./exchangerate/pom.xml

#Ejecucion de script docker compose para crear imagenes e iniciar contenedores
echo -e "${BLUE_COLOR}Ejecucion de script docker compose para crear imagenes e iniciar contenedores${NO_COLOR}"

docker compose -f exchcl-docker-compose.yml up -d