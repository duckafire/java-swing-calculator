### Code architecture

* Separate the **interface** and the **math logic**:
	* Interface:
		* Receive the user input.
		* Filter the user input.
		* Show captured informations.
		* Send user input to math logic.
	* Math logic:
		* Receive the interface input.
		* Store values (like *double*).
		* Return the storage state to interface
		* Calculate result and send it to interface
* Improvements in object communication and events/actions.

---

### Enhancement

* Advanced mode:
	* New buttons:
		* Open parentesis
		* Close parentesus
		* Root
		* Sine (normal; arc; hyperbolic)
		* Consseno (normal; arc; hyperbolic)
		* Tangent (normal; arc; hyperbolic)
		* PI
		* Logarithm
		* Absolute value
	* Features:
		* +24 decimal places
* Before a number is placed, operators will be stored and shown in a reservated display.
	* Therefore instead to show "1+2-", it will show "1+2", and the '-' will be stored in a reservated viewer.
* Possibility of digit with physical keyboard.

---

## Style

* Different colors to elements
* Light and dark mode
* Buttons with rounded corners
* Small space between buttons
