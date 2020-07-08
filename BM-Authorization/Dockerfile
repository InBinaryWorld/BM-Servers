FROM jboss/keycloak:10.0.2

ENV KEYCLOAK_REALM_INPUT=./business-manager.json
ENV KEYCLOAK_REALM_OUTPUT=/tmp/business-manager.json
ENV KEYCLOAK_IMPORT=${KEYCLOAK_REALM_OUTPUT}

ENV KEYCLOAK_USER=admin
ENV KEYCLOAK_PASSWORD=admin
ENV DB_DATABASE=keycloak
ENV DB_USER=keycloak
ENV DB_PASSWORD=password

COPY ${KEYCLOAK_REALM_INPUT} ${KEYCLOAK_REALM_OUTPUT}

CMD ["-b","0.0.0.0","-Dkeycloak.profile=preview", "-Dkeycloak.profile.feature.token_exchange=enabled", "-Dkeycloak.profile.feature.admin_fine_grained_authz=enabled"]
