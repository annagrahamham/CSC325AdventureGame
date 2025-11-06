
# Project name and summary
Project Goal: Create a text-based adventure game that utilizes multithreading to simulate concurrent character actions.
Name: Ocean Game
Summary: A text-based adventure game set in an ocean environment where predators and prey interact in real-time using multithreading. Currently features orcas, seals, and a human fisherman.

# Key features and intended use cases
- Multithreaded character actions allowing for simultaneous behaviors.
- Predator and prey interactions involving attack and defense mechanisms.
- Basic health management for game characters.
- Expandable design for adding new characters and behaviors.

# API Added
The project includes `SeaConditionsAPIClient` which fetches marine data from Open-Meteo's Marine Weather API (https://open-meteo.com/en/docs/marine-weather-api). The client calls Open-Meteo directly and the `GameEngine` reads wave and wind values from the response.


# Technologies used
- Java
- Multithreading (Java Threads)
- Java HTTP Client for API calls


# Setup instructions
1. Clone the repository to your local machine.
2. Open the project in your preferred Java IDE.
3. Run the main class to start the game.

