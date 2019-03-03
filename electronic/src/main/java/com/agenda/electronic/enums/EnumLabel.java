package com.agenda.electronic.enums;

public enum EnumLabel {
    REGISTRAR_LABEL("Registrar"),
    NUEVO_LABEL("Nuevo"),
    EDITAR_LABEL("Editar"),
    ELIMINAR_LABEL("Eliminar"),
    ACEPTAR_LABEL("Aceptar"),
    ROW_COUNT_CAPTION_LABEL("%d registro(s) encontrados"),
    REFRESCAR_LABEL("Refrescar"),
    ELIMINAR_REGISTRO_LABEL("¿Desea eliminar el registro?"),
    NAME_ROL_LABEL("Nombre de rol"),
    DESCRIPTION_ROL_LABEL("Descripcion de rol"),
    USERNAME_LABEL("Nombre de usuario"),
    RIF_LABEL("Rif"),
    TEMA_LABEL("Tema"),
    PASSWORD_LABEL("Contraseña"),
    DURACION_LABEL("Duracion"),
    FIRST_NAME_LABEL("Primer Nombre"),
    NAMES_LABEL("Nombres"),
    UBICACION_LABEL("Ubicacion"),
    LAST_NAME_LABEL("Apellidos"),
    IDENTITY_DOCUMENT_LABEL("Documento de identidad"),
    AGE_LABEL("Edad"),
    PHONE_NUMBER_LABEL("Numero de telefono"),
    MARCA_LABEL("Marca"),
    INIT_DATE_LABEL("Fecha inicio"),
    FINISH_DATE_LABEL("Fecha fin"),
    MODELO_LABEL("Modelo"),
    IMEI_LABEL("Imei"),
    NUMERO_TELEFONO_LABEL("N° Telefono"),
    VEHICULO_LABEL("Vehiculo"),
    USER_LABEL("Usuario"),
    DEVICE_LABEL("Dispositivo"),
    ROUTES_LABEL("Rutas"),
    EMAIL_LABEL("Email"),
    LICENSEPLATE_LABEL("Placa"),
    ROUTE_NAME_LABEL("Ruta"),
    CANCELAR_LABEL("Cancelar"),
    NAME_ROUTE("Nombre de Ruta"),
    DESCRIPTION_ROUTE("Descripcion de Ruta"),
    ROL_LABEL("Roles"), 
    MATRICULA_LABEL("Matricula"),
    YEAR_LABEL("Año"),
    TONELADAS_LABEL("Toneladas"), DOWNLOAD("Descargar");

    EnumLabel(String label) {
        this.label = label;
    }

    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
