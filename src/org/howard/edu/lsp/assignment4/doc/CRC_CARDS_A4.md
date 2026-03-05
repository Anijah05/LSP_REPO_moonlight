CRC Card
Class: TransponderReceiver
Responsibilities:

- Receive high-density transponder packets from arriving aircraft
- Validate packet integrity (basic checks)
- Forward raw packets for decoding
  Collaborators (if any):
- PacketDecoder
  Assumptions (if any):
- Packets arrive through a hardware/network interface provided by the airport

---

CRC Card
Class: PacketDecoder
Responsibilities:

- Unpack high-density packets into readable flight data fields
- Normalize decoded values into a consistent internal format
- Report decoding failures without crashing the pipeline
  Collaborators (if any):
- AircraftFactory
- IngestionLogger
  Assumptions (if any):
- Packet format is known and versioned

---

CRC Card
Class: AircraftFactory
Responsibilities:

- Create aircraft domain objects from decoded flight data
- Map aircraft type codes to aircraft types/models
- Reject incomplete or invalid decoded data
  Collaborators (if any):
- Aircraft
- FlightTrack
  Assumptions (if any):
- Each packet contains a unique aircraft identifier (transponder ID)

---

CRC Card
Class: AircraftDatabase
Responsibilities:

- Store aircraft records keyed by aircraft identifier
- Update stored aircraft state using the latest flight track data
- Provide lookup/query operations for controller requests
- Provide current aircraft snapshots for display building
  Collaborators (if any):
- Aircraft
- FlightTrack
  Assumptions (if any):
- Database is in-memory for fast reads/updates

---

CRC Card
Class: Aircraft
Responsibilities:

- Represent an aircraft’s identity and type
- Maintain the aircraft’s latest flight state (position, altitude, speed, heading)
- Apply updates from new flight track data
  Collaborators (if any):
- FlightTrack
  Assumptions (if any):
- Aircraft type may affect safety thresholds (separation rules)

---

CRC Card
Class: FlightTrack
Responsibilities:

- Represent a single decoded flight data update with a timestamp
- Provide flight data fields used for display and safety analysis
  Collaborators (if any):
- Aircraft
  Assumptions (if any):
- A timestamp is included in the packet or added at receipt time

---

CRC Card
Class: DisplaySnapshotBuilder
Responsibilities:

- Build a display-ready snapshot from current aircraft database state
- Convert aircraft state into display elements (labels, symbols, positions)
- Filter out stale aircraft records if needed
  Collaborators (if any):
- AircraftDatabase
- DisplaySnapshot
  Assumptions (if any):
- “On screen” aircraft are determined by airport airspace bounds

---

CRC Card
Class: DisplaySnapshot
Responsibilities:

- Hold display elements for one refresh cycle
- Support mapping from display elements to aircraft identifiers for selection
  Collaborators (if any):
- DisplayElement
  Assumptions (if any):
- Snapshot is immutable once created to avoid mid-refresh changes

---

CRC Card
Class: DisplayElement
Responsibilities:

- Represent one aircraft’s display symbol and label information
- Store screen position and display attributes derived from flight data
  Collaborators (if any):
- Aircraft
  Assumptions (if any):
- Screen coordinates are derived from geographic coordinates using a known projection

---

CRC Card
Class: DisplayRefresher
Responsibilities:

- Trigger display updates every 10 seconds
- Request a new snapshot and publish it to the controller display
- Ensure refreshing continues even if some data is missing
  Collaborators (if any):
- DisplaySnapshotBuilder
- ControllerDisplay
  Assumptions (if any):
- System timer/clock service is available

---

CRC Card
Class: ControllerDisplay
Responsibilities:

- Render the latest display snapshot for the controller
- Allow the controller to select an aircraft shown on screen
- Show detailed information returned from controller queries
  Collaborators (if any):
- DisplaySnapshot
- ControllerQueryService
  Assumptions (if any):
- Rendering technology is out of scope for this design

---

CRC Card
Class: SafetyAnalyzer
Responsibilities:

- Analyze current aircraft states to detect dangerous situations
- Evaluate risk rules (separation, altitude conflicts, etc.)
- Produce hazard events for alerting
  Collaborators (if any):
- AircraftDatabase
- ConflictDetector
- AlertManager
  Assumptions (if any):
- Dangerous situations focus on aircraft-to-aircraft conflicts

---

CRC Card
Class: ConflictDetector
Responsibilities:

- Detect potential conflicts between aircraft tracks
- Determine severity using distance, altitude, and time-to-conflict
- Return conflict events for alerting
  Collaborators (if any):
- Aircraft
- FlightTrack
- SafetyAlert
  Assumptions (if any):
- Separation thresholds are configurable by policy

---

CRC Card
Class: AlertManager
Responsibilities:

- Convert detected hazards into controller-facing alerts
- Deduplicate repeated alerts across refresh cycles
- Expire alerts when hazards clear
  Collaborators (if any):
- SafetyAlert
- ControllerDisplay
  Assumptions (if any):
- Alerts are visible to the controller during display refreshes

---

CRC Card
Class: SafetyAlert
Responsibilities:

- Represent a dangerous situation (type, severity, aircraft involved, timestamp)
- Provide an alert message payload suitable for controller display
  Collaborators (if any):
- Aircraft
  Assumptions (if any):
- Alerts reference aircraft by identifier and can include key flight details

---

CRC Card
Class: ControllerQueryService
Responsibilities:

- Handle controller requests for aircraft details
- Retrieve aircraft records and relevant flight state from the database
- Format aircraft details for display to the controller
  Collaborators (if any):
- AircraftDatabase
- Aircraft
  Assumptions (if any):
- Queries are initiated by selecting an aircraft currently shown on screen

---

CRC Card
Class: IngestionLogger
Responsibilities:

- Record packet reception and decoding errors
- Track counts of received, decoded, and rejected packets
- Support troubleshooting of ingestion pipeline activity
  Collaborators (if any):
- TransponderReceiver
- PacketDecoder
  Assumptions (if any):
- Logging is lightweight and does not block real-time processing
