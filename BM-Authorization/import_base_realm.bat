.\bin\standalone.sh -Djboss.socket.binding.port-offset=100 -Dkeycloak.migration.action=import -Dkeycloak.migration.provider=singleFile  -Dkeycloak.migration.file=base.json -Dkeycloak.migration.strategy=OVERWRITE_EXISTING
