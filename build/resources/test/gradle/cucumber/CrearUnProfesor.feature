Feature: Como usuario quiero registrar un profesor en el sistema 

  Scenario: Registrar un profesor en el sistema 
    Given inicia sesion
    When registro un profesor
    Then valido su registro
