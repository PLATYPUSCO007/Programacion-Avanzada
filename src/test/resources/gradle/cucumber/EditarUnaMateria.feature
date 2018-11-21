Feature: Como usuario quiero editar una materia

  Scenario: Editar una materia con sus datos
    Given Ready a session
    When creo una materia nueva 
    And edito el codigo de la materia
    Then valido la edicion
