FROM jboss/keycloak:9.0.3
 
RUN bash /opt/jboss/keycloak/bin/add-user-keycloak.sh -u admin -p admin


EXPOSE 8080
EXPOSE 8443

COPY ./base.json /tmp/base.json

CMD ["-Dkeycloak.profile=preview", "-Dkeycloak.profile.feature.token_exchange=enabled", "-Dkeycloak.profile.feature.admin_fine_grained_authz=enabled", "-Djboss.socket.binding.port-offset=100" "-Dkeycloak.migration.action=import" "-Dkeycloak.migration.provider=singleFile"  "-Dkeycloak.migration.file=base.json" "-Dkeycloak.migration.strategy=OVERWRITE_EXISTING"] 