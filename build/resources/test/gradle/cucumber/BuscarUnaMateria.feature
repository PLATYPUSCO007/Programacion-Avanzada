Feature: Como usuario quiero buscar una materia

  Scenario: Buscar una materia con sus datos
    Given iniciamos session
    When creo una materia 
    And busco la materia
    Then obtengo sus datos
