Feature: Como usuario quiero eliminar un estudiante en el sistema

  Scenario: Eliminar un estudiante en el sistema
    Given inicio sesion
    When creo un estudiante
    And elimino el estudiante de la BD
    Then valido la eliminacion
