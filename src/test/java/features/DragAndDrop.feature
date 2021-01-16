Feature: DragAndDropTest
  Scenario: Test Drag and Drop module
    Given I'm in TalentTek drag and drop page
    When I drag TalentTek object into new destination
    Then I verify that TalentTek became TalentTek IT TC
