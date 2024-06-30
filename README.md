# Appointment service
## Description
Microservice that is a part of AppointmentManager app. Its job is to create, delete and fetch appointment data.

## Api endpoints
- Create appointment:
  - path: /api/v1/appointment/
  - method: POST
  - description: receives appointment data and seves it in the database. It also sends the request to availability service to mark the availability as booked.

- Delete appointment:
  - path: /api/v1/appointment/{id}
  - method: DELETE
  - description: receives appointment id as a path variable and deletes the record in the database. It also sends the request to availability service to mark the availability as free.

- Get appointments for user:
  - path: /api/v1/appointment/user/{patientId}
  - method: GET
  - description: receives patient id as a path variable and returns all appointments for this patient.

- Get appointments for doctor:
  - path: /api/v1/appointment/doctor/{doctorId}
  - method: GET
  - description: receives doctor id as a path variable and returns all appointments for this doctor.
