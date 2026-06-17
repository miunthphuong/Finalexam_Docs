const apiRoot = '/api/v1';

async function fetchStudents() {
  const res = await axios.get(`${apiRoot}/students`);
  return res.data;
}

async function fetchCourses() {
  // courses endpoint not implemented; derive from students or add one
  const res = await axios.get(`${apiRoot}/students`);
  const students = res.data;
  const map = new Map();
  students.forEach(s => s.courses.forEach(c=> map.set(c.courseId, c)));
  return Array.from(map.values());
}

function renderStudents(students) {
  const container = document.getElementById('students');
  container.innerHTML = '';
  students.forEach(s => {
    const item = document.createElement('div');
    item.className = 'list-group-item';
    item.innerHTML = `<div class="d-flex w-100 justify-content-between">
        <h5 class="mb-1">${s.firstName} ${s.lastName} <small class="text-muted">#${s.studentId}</small></h5>
        <small class="text-muted">Total Credits: ${s.totalCreditScore}</small>
      </div>
      <p class="mb-1">Matric: ${s.matricNumber} | Admission: ${s.dateOfAdmission || ''}</p>
      <div>${s.courses.map(c=>`<span class="badge bg-secondary course-badge">${c.courseCode} (${c.creditScore})</span>`).join('')}</div>`;
    container.appendChild(item);
  });
}

async function load() {
  try {
    const [students, courses] = await Promise.all([fetchStudents(), fetchCourses()]);
    renderStudents(students);
    const sel = document.getElementById('courseId');
    sel.innerHTML = courses.map(c=>`<option value="${c.courseId}">${c.courseCode} - ${c.courseName} (${c.creditScore})</option>`).join('');
  } catch(e){
    console.error(e);
    document.getElementById('msg').innerHTML = `<div class="alert alert-danger">Failed to load data</div>`;
  }
}

document.getElementById('regForm').addEventListener('submit', async e => {
  e.preventDefault();
  const payload = {
    // parse studentId as number or null
    studentId: (() => {
      const v = document.getElementById('studentId').value;
      if (!v) return null;
      const n = Number(v);
      if (Number.isNaN(n)) return null;
      return Math.trunc(n);
    })(),
    matricNumber: document.getElementById('matricNumber').value,
    firstName: document.getElementById('firstName').value,
    lastName: document.getElementById('lastName').value,
    dateOfAdmission: document.getElementById('dateOfAdmission').value || null,
    courseId: parseInt(document.getElementById('courseId').value)
  };
  try{
    const res = await axios.post(`${apiRoot}/registrations`, payload);
    document.getElementById('msg').innerHTML = `<div class="alert alert-success">Enrolled: ${res.data.firstName} ${res.data.lastName} (ID: ${res.data.studentId})</div>`;
    load();
  }catch(err){
    console.error(err);
    const text = err.response?.data?.message || err.response?.data || err.message;
    document.getElementById('msg').innerHTML = `<div class="alert alert-danger">${text}</div>`;
  }
});

window.onload = load;