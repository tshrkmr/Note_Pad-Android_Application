# Note_Pad-Android_Application
Notes Application
This app allows the creation and maintenance of multiple notes. Any number of notes are allowed (including no
notes at all). Notes are made up of a title, a note text, and a last-update time.
• Notes aresaved to (and loaded from) the internal file system in JSON format. If no file is found upon 
loading, the application starts with no existing notes and no errors. (A new JSON file is then created when new notes are saved).
• JSON file loading happens in the onCreate method. Saving happens in the onPause method.
• A simple java Note class (with title, note text, and last save date) is created to represent each individual
note in the application.
