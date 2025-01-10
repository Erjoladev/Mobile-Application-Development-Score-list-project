# Mobile-Application-Development-Score list project

 This is an Android application in Kotlin to manage and display a list of players, their scores, and ranks. The application includes features for adding players, persisting data, and managing score updates across activities.

Features
## 1. Main Activity
Player List:

Displays a list of players using a RecyclerView. Each player has a name (first name only), a score, and a rank.
Players are sorted in descending order of their scores.
Add Player:

A text box (EditText) and a button allow users to add a new player. Newly added players start with a score of 0.
Persistent Data:
Player data is saved in an internal file (joueurs.json) in JSON format. Methods are provided to save (write to file) and load (read from file) the player list.

## 2. Persistent Player Data
Save Data:
Converts the list of players to a JSON string and writes it to the internal storage.
Load Data:
Reads from the internal file and populates the list of players on app launch.

## 3. Player Details Activity
Individual Player View:
When a player is clicked in the main activity, a new screen opens displaying the player's name, score, and rank.
Score Updates:
Buttons allow users to add 1 or 10 points to the player’s score.
The updated score is reflected in the main activity when the user navigates back.

## 4. Player Ranking
Rank Calculation:
Players are assigned a rank based on their scores:
Rank 1: 0–49 points.
Rank 2: 50–99 points.
Rank 3: 100+ points.
The rank is dynamically updated whenever the score changes.

## 5. Sorting
Sort Players:
The player list is sorted by score in descending order. Sorting is done at the collection level (ArrayList) before notifying the RecyclerView adapter.

