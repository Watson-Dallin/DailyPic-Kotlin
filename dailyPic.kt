// Gets date information from device clock
import java.time.LocalDateTime
// Formats the date into usable strings 
import java.time.format.DateTimeFormatter
// Creates a frame for our image. 
import javax.swing.JFrame 
// This allows for using image objects.
import javax.swing.ImageIcon
// This displays the image object in the frame.
import javax.swing.JLabel 
// This is to check if the desired image file exists.
import java.io.File


/********************************************************
* Main's job is to call the respective functions needed
* for the program.
*********************************************************/
fun main() {
    // Get today's date as a string of format MM-dd
    val today = getToday()
    
    // Get a valid image name to pass to displayImg or 
    // False if no image exists.
    val imgName = createImgName(today)

    // If their is an image, show it. Else, apologize.
    if (imgName != "") {
        displayImg(imgName)
    }
    else {
        println("Sorry, no file exists for today's date")
    }
}

/********************************************************
* getToday uses the DateTime module to get the date from
* the computer clock.
*********************************************************/
fun getToday(): String {
    // Get today's date from the clock.
    val currentDateTime = LocalDateTime.now()

    // Return today's date to main.
    return currentDateTime.format(DateTimeFormatter.ofPattern("MM-dd"))
}

/********************************************************
* createImgName take the parameter of today's MM-dd date
* string and turns it into a filename of one of the stored
* image files.
*********************************************************/
fun createImgName(today: String): String {
    // Add a filetype to the image name and check if the file exists.
    // Images could be of types .png .jpg .jpeg .gif. If multiple files
    // exist for the same day using different types one is to be returned
    // in that order of priority.

    // List of image types we want to check for in priority order.
    val types = listOf(".png", ".jpg", ".jpeg", ".gif")

    // This will visit each type in types.
    types.forEach {

        // This concatenates a file type onto the today string to make a filename.
        // forEach makes refers to the current object iteration as "it". 
        val filename = today.plus(it)

        // Create a file object using that filename.
        var file = File(filename)
        
        // Check if the file exists and return the successful filename to main. 
        if (file.exists()){
            return filename
        }
    }
    // If there is no valid filename of any type then return 
    // false so main knows not to call displayImg with a bad
    // filename.
    return ""
}


/********************************************************
* displayImg puts together our frame and icon and sticks
* them together into a displayable object for the user
* to see.
*********************************************************/
fun displayImg(img_name: String) {
    // Initialize the frame and the icon to go in the frame.
    val frame = JFrame()
    val icon = ImageIcon(img_name)

    // Labe the turns the icon into something that fits in a frame.
    val label = JLabel(icon)
    frame.add(label)

    // Set close behavior
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    
    // Put it all together and close it.
    frame.pack()
    frame.setVisible(true)
}