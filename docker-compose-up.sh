#!/bin/sh

#Constantes de variables para script
BLUE_COLOR='\033[0;34m'
NO_COLOR='\033[0m'

#Ejecucion de script docker compose para crear imagenes e iniciar contenedores
echo -e "${BLUE_COLOR}Ejecucion de script docker compose para crear imagenes e iniciar contenedores${NO_COLOR}"

docker compose -f exchcl-docker-compose.yml up -d