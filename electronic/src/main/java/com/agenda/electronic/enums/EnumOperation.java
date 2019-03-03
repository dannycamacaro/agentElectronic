package com.agenda.electronic.enums;


public enum EnumOperation {

    //MODULO USER
    ADD_USER("1", "Agregar usuario"),
    EDIT_USER("2", "Editar usuario"),
    DELETE_USER("3", "Eliminar usuario"),
    //MODULO DEVICE
    ADD_DEVICE("4", "Agregar dispositivo"),
    EDIT_DEVICE("5", "Editar dispositivo"),
    DELETE_DEVICE("6", "Eliminar dispositivo"),
    //MODULO DRIVER
    ADD_DRIVER("7", "Agregar chofer"),
    EDIT_DRIVER("8", "Editar chofer"),
    DELETE_DRIVER("9", "Eliminar chofer"),
    //MODULO LOCATION
    ADD_LOCATION("10", "Agregar locacion"),
    EDIT_LOCATION("11", "Editar locacion"),
    DELETE_LOCATION("12", "Eliminar locacion"),
    //MODULO ROL
    ADD_ROL("13", "Agregar rol"),
    EDIT_ROL("14", "Editar rol"),
    DELETE_ROL("15", "Eliminar rol"),
    //MODULO ROUTES
    ADD_ROUTES("16", "Agregar rutas"),
    EDIT_ROUTES("17", "Editar rutas"),
    DELETE_ROUTES("18", "Eliminar rutas"),
    //MODULO ROUTES_DETAIL
    ADD_ROUTES_DETAIL("19", "Agregar detalle de rutas"),
    EDIT_ROUTES_DETAIL("20", "Editar detalle de rutas"),
    DELETE_ROUTES_DETAIL("21", "Eliminar detalle de rutas"),
    //MODULO VEHICLE
    ADD_VEHICLE("22", "Agregar vehiculos"),
    EDIT_VEHICLE("23", "Editar vehiculos"),
    DELETE_VEHICLE("24", "Eliminar vehiculos"),
    //MODULO ASSIGNEDVEHICLE
    ADD_ASSIGNEDVEHICLE("25", "Agregar asignar vehiculos"),
    EDIT_ASSIGNEDVEHICLE("26", "Editar asignar vehiculos"),
    DELETE_ASSIGNEDVEHICLE("27", "Eliminar asignar vehiculos"),
    //MODULO ASSIGNEDROUTES
    ADD_ASSIGNEDROUTES("28", "Agregar asignar rutas"),
    EDIT_ASSIGNEDROUTES("29", "Editar asignar rutas"),
    DELETE_ASSIGNEDROUTES("30", "Eliminar asignar rutas");

    EnumOperation(String idOperation, String operationName) {
        this.idOperation = idOperation;
        this.operationName = operationName;

    }

    public String getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(String idOperation) {
        this.idOperation = idOperation;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    String idOperation;
    String operationName;

}
