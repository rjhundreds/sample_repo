@AssurityAPI
Feature: Assurity API Validation

	@ExerciseValidation
	Scenario: Response Validation exercise
	
	  Given I have API
    When I send request at API
		Then status code comes as 200
		And json body contain value 6329 in path 'CategoryId' 
		Then json body contain value 'Home & garden' in path 'Name' 
		Then json body contain value true in path 'CanRelist' 
		And I validate 'Promotions' element with 'Name' equals Feature has a 'Description' that contains the text 'Better position in category' 
		
	
	
	
	
	
	
		

