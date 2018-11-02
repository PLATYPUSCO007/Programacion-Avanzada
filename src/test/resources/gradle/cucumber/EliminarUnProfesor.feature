Feature: Como usuario quiero buscar un profesor en el sistema 

  Scenario: Buscar un profesor en el sistema 
    Given iniciamos la sesion
    When registramos a un profesor
    Then lo eliminamos del sistema
    And valido eliminacion
