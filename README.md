🛠️ Grievance Redressal Portal

A full-stack grievance management system that allows users to submit complaints (with optional anonymity and file uploads) and enables administrators to track, manage, analyze, and export complaint data efficiently.

🚀 Features
👤 User Module

User authentication & role-based access

Submit complaints with:

Title, description, category, priority

Optional file upload (image/document)

Anonymous or public submission

Auto-generated Complaint ID (e.g. 20240715-001)

View only their own complaints

Track complaint status:

Submitted

In Progress

Resolved

🛡️ Admin Module

View all complaints (including anonymous)

See complaint details:

Complaint ID

User name & email (if not anonymous)

Date & time of submission

Update complaint status

Delete complaints

Analytics dashboard:

Total complaints

Open (Submitted + In Progress)

Resolved

Export reports:

CSV export with date & category filters

📊 Reports & Analytics

Complaint trends by:

Status

Category

Time range

CSV export for:

Audits

Reviews

External reporting

🧱 Tech Stack
Frontend

React.js

Tailwind CSS

Axios

React Hooks

Backend

Spring Boot

Spring Data JPA

Spring Security

REST APIs

Database

MySQL

File Handling

Multipart file upload

Server-side storage

Secure attachment access

🧩 Complaint ID Format

Each complaint gets a human-readable unique ID:

YYYYMMDD-XXX


Example:

20240715-001
20240715-002


Resets daily

Guaranteed uniqueness

Generated at backend

🔐 Security Rules
Role	Access
User	Only their own complaints
Admin	All complaints
Anonymous complaints	Admin only

✔ Anonymous complaints are never visible to users
✔ Secure backend filtering (not frontend-based)

📁 Project Structure (Backend)
src/main/java/com/grievance/portal
│
├── controller
│   ├── ComplaintController
│   └── AdminReportController
│
├── service
│   └── impl
│       └── ComplaintServiceImpl
│
├── repository
│   └── ComplaintRepository
│
├── entity
│   ├── Complaint
│   └── User
│
└── config
    ├── WebConfig
    └── JacksonConfig

📁 Project Structure (Frontend)
src
├── layouts
│   ├── UserLayout.jsx
│   └── AdminLayout.jsx
│
├── pages
│   ├── user
│   │   ├── SubmitComplaint.jsx
│   │   └── MyComplaints.jsx
│   │
│   └── admin
│       ├── AdminDashboard.jsx
│       ├── AllComplaints.jsx
│       └── AdminAnalytics.jsx
│
├── services
│   └── api.js

⚙️ Setup Instructions
Backend

Configure MySQL database

Update application.properties

Run Spring Boot application

Frontend
npm install
npm start

📌 API Highlights
Method	Endpoint	Description
POST	/api/complaints/upload	Submit complaint with file
GET	/api/complaints/user/{id}	User complaints
GET	/api/complaints	Admin – all complaints
PUT	/api/complaints/{id}/status	Update status
GET	/api/admin/reports/analytics	Analytics
GET	/api/admin/reports/export/csv	Export CSV
🧠 Learning Outcomes

REST API design

Role-based access control

File upload handling

Backend-driven analytics

Real-world complaint workflow

Secure data isolation per user

📜 License

This project is developed for educational purposes and can be extended for real-world use.

✨ Author

Karan Kumar D
B.Tech IT
RMK Engineering College
