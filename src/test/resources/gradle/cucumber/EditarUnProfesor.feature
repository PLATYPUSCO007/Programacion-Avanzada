Feature: Como usuario quiero editar un profesor en el sistema 

  Scenario: Editar un profesor en el sistema 
    Given iniciamos sesion
    When registro a un profesor
    Then editamos su nombre
    And valido la modificacion
