# Flashify
Flashcard learning tool leveraging AI for rapid flashcard generation

## Directions for use:
1. Obtain API Key from OpenAI at [this link](https://platform.openai.com/account/api-keys)
2. In your local.properties file that located in the root project directory, insert the following line of code:
`apiKey=YOUR_API_KEY` 
where YOUR_API_KEY is the key from OpenAI

Note: the loading screen that waits for an API response will time out after two minutes. 


## Design:

Flashcards classes, nested within Category classes. 



## Folder Structure:
For now, structure is one layer of any number of categories, each of which contain any number of flaschards. 
