
// UNUSED FILE
import java.awt.BorderLayout
import java.awt.Font
//import java.awt.event.ActionEvent
import java.awt.event.ActionListener
//import kotlin.random.Random
import javax.swing.*
import kotlin.math.pow
import kotlin.math.sqrt

class CarSimulationGUI : JFrame("Car Simulation") {
    private val car = Car()
    private val infoLabel = JLabel()
    private var isSimulationRunning = false

    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(400, 200)

        val startButton = JButton("Start")
        startButton.addActionListener { startSimulation() }

        val stopButton = JButton("Stop")
        stopButton.addActionListener { stopSimulation() }

        val controlPanel = JPanel()
        controlPanel.add(startButton)
        controlPanel.add(stopButton)

        add(controlPanel, BorderLayout.NORTH)
        // Set the font size for infoLabel
        infoLabel.font = Font("Arial", Font.PLAIN, 16)
        add(infoLabel, BorderLayout.CENTER)
    }

    private fun startSimulation() {
        isSimulationRunning = true
        car.area = getRandomArea() // Set initial area randomly
        Timer(900, ActionListener {
            if (!isSimulationRunning) {
                return@ActionListener
            }

            // Simulate random values for dt, vy, speed, distance, and location
            car.dt = Math.random() * 0.2 + 0.05 // Random dt between 0.05 and 0.2 seconds
            car.vy = Math.random() * 10 // Random vy between -5.0 and 5.0 m/s
            car.vx = Math.random() * 15 // Random speed between 5.0 and 20.0 m/s
            car.distance = Math.random() * 150 + 50 // Random distance between 50.0 and 200.0 meters

            car.updateTime()
            car.updateSpeed()
            car.updateAcceleration()
            car.updateDistance()

            updateInfoLabel()

            if (car.time >= 10.0) {
                stopSimulation()
            }
        }).start()
    }


    private fun stopSimulation() {
        isSimulationRunning = false
        infoLabel.text = "\nFinal Results: \n\n" + getResults()
    }

    private fun updateInfoLabel() {
        infoLabel.text =
            "\nDistance travelled: %.2fm  \nSpeed: %.2fkm/hr  \nAcceleration: %.2fkm/hr^2  \nLocation: %s".format(
                car.distance,
                car.speed,
                car.acceleration,
                car.area
            )
    }

    private fun getResults(): String {
        return "\nDistance travelled: %.2fm  \nSpeed: %.2fkm/hr  \nAcceleration: %.2fkm/hr^2  \nLocation: %s".format(
            car.distance,
            car.speed,
            car.acceleration,
            car.area
        )

    }

    private fun getRandomArea(): String {
        val areas = listOf("School", "Playground", "Highway", "Urban Area", "Rural Area")
        return areas.random()
    }
}

fun main() {
    SwingUtilities.invokeLater { CarSimulationGUI().isVisible = true }
}

class Car(
    var area: String = "Initial Area",
    var vx: Double = 0.0,
    var vy: Double = 0.0,
    var dt: Double = 0.1
) {
    var distance = 0.0
    var time = 0.0
    var speed = 0.0
    var acceleration = 0.0


    fun updateSpeed() {
        // Calculate speed using the Euclidean norm of velocity
        speed = sqrt(vx.pow(2) + vy.pow(2)) / dt
        //speed = vy - vx / dt
    }

    fun updateAcceleration() {
        // Calculate acceleration using the change in velocity (vy) and time (dt)
        acceleration = speed / dt
    }

    fun updateDistance() {
        // Update distance using the speed and time
        distance += speed * dt
    }

    fun updateTime() {
        time += dt
    }
}

