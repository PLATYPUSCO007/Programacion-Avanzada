Feature: Como usuario quiero crear un estudiante en el sistema con sus datos personales

  Scenario: Crear un estudiante en el sistema con sus datos personales
    Given un inicio de sesion
    When creo un estudiante con sus datos personales
    Then valido su creacion
