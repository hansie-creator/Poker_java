# Poker Java – Texas Hold’em (Player vs Bot)

This project is a simple **Texas Hold’em poker game implemented in Java**, where one human player plays against a computer-controlled bot.  
The game includes betting logic, multiple game phases, and an automatic showdown with winner determination.

---

## Features

- Texas Hold’em game flow:
  - Pre-Flop  
  - Flop  
  - Turn  
  - River  
  - Showdown
- One human player vs one bot
- Betting actions:
  - Check
  - Call
  - Raise
  - Fold
- Automatic pot and chip handling
- Community cards (table cards)
- Hand evaluation and winner determination
- Bot with basic decision-making logic

---

## Game Flow

1. Both players receive 2 cards  
2. Betting round (Pre-Flop)  
3. Flop (3 community cards) → betting round  
4. Turn (1 card) → betting round  
5. River (1 card) → betting round  
6. Showdown:
   - Bot cards are revealed
   - Best hand is evaluated
   - Winner is printed to the console

The game only moves to the next phase when:
- Both players have acted **and**
- There is no open raise remaining

---

## Project Structure (Main Classes)

- `Pokerspel`  
  Controls the overall game logic, phases, betting, and winner calculation.

- `PokerBot`  
  Contains the decision logic for the bot (call / raise / fold).

- `Kaart`, `KaartDeck`  
  Card representation and deck handling.

- `HandEvaluator`  
  Evaluates the strength of a poker hand using 7 cards.

- `Fase`  
  Enum representing PRE_FLOP, FLOP, TURN, RIVER, and SHOWDOWN.

---

## Technical Details

- Language: **Java**
- Console-based output
- Object-oriented design

---

## Known Limitations

- Bot uses simple logic (no advanced AI)
- No split pot handling for tied hands
- Only one human player
- Fixed raise amount

---

## Possible Improvements

- Multiple players and/or bots
- Split pot support
- Improved hand evaluation
- More realistic bot behavior (probabilities, bluffing)
- Variable blinds and stack sizes

---

## Authors
Hannes :Student - IIW Uhasselt,
Jasper :Student – IIW Uhasselt
