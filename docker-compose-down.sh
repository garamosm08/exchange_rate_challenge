#!/bin/sh

#Constantes de variables para script
BLUE_COLOR='\033[0;34m'
NO_COLOR='\033[0m'

#Ejecucion de script docker compose para borrar objetos creados
echo -e "${BLUE_COLOR}Ejecucion de script docker compose para borrar objetos creados${NO_COLOR}"

docker compose -f exchcl-docker-compose.yml down