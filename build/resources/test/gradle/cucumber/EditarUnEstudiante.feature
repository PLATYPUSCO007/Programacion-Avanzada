Feature: Como usuario quiero editar un estudiante en el sistema con sus datos personales

  Scenario: Editar un estudiante en el sistema con sus datos personales
    Given iniciar de sesion
    When edito un estudiante
    Then valido su edicion
