# CV Builder — JavaFX Maven Application

A simple JavaFX application for creating and previewing a basic CV. Users can enter personal information, upload a profile picture, and view a formatted preview.

## Features

* Personal Information Input

  * Full Name (required)
  * Email (required, validated)
  * Phone Number (optional, digits only)
  * Address
  * Educational Qualification
  * Skills
  * Work Experience
  * Projects

* Profile Picture Upload

  * Supports PNG, JPG, and JPEG
  * Preview displayed in the CV view

* Client-Side Validation

  * Full Name must not be empty
  * Email is validated with a regex
  * Phone Number (optional): 7–15 digits

* CV Preview Screen

  * Displays all entered information
  * Shows profile picture if provided
  * Styled using CSS

* Dialog-Based Feedback

  * Error dialogs for invalid inputs
  * Success dialog on CV creation

## Quick Start

1. Clone the repository:

   ```bash
   git clone https://github.com/Tahmid2K22/mahin_2207109__CVBuilder.git
   ```
2. Open the project in an IDE (IntelliJ recommended).
3. Run the application
4. Click "Create your CV" on the home screen.
5. Fill in the form and optionally upload a profile picture.
6. Click "Create your CV" to view the preview.

## Project Structure

```
src/main/java/com/example/mahin_2207109__cvbuilder/
  CV_Builder.java            // Main application
  HomeController.java        // Home screen controller
  inputController.java       // Input form, validation, image handling
  show_controller.java       // CV preview controller

src/main/resources/com/example/mahin_2207109__cvbuilder/
  Home.fxml                  // Home screen layout
  cv_input.fxml              // Input form layout
  CV_show.fxml               // CV preview layout

src/main/resources/
  CV_show.css               // Styling for CV preview
```

## Validation Rules

| Field        | Requirement                  |
| ------------ | ---------------------------- |
| Full Name    | Required                     |
| Email        | Must match basic email regex |
| Phone Number | Optional; 7–15 digits        |

## Notes

* Profile pictures are loaded using file-system URIs.
* If image loading fails, the preview shows no picture.
* To modify validation or add fields, update:

  * `inputController.java`
  * Related `.fxml` files


