import React,{useEffect,useState} from 'react'; import {createRoot} from 'react-dom/client'; import './style.css';
const API='http://localhost:8080/api';
function App(){
 const [employees,setEmployees]=useState([]),[leaves,setLeaves]=useState([]),[name,setName]=useState(''),[email,setEmail]=useState('');
 const load=()=>{fetch(API+'/employees').then(r=>r.json()).then(setEmployees);fetch(API+'/leaves').then(r=>r.json()).then(setLeaves)};
 useEffect(load,[]);
 const add=async()=>{await fetch(API+'/employees',{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify({name,email,role:'EMPLOYEE'})});setName('');setEmail('');load()};
 return <main><h1>Employee Leave & Attendance</h1><section className="card"><h2>Add Employee</h2><input placeholder="Name" value={name} onChange={e=>setName(e.target.value)}/><input placeholder="Email" value={email} onChange={e=>setEmail(e.target.value)}/><button onClick={add}>Create</button></section>
 <section className="card"><h2>Employees ({employees.length})</h2>{employees.map(e=><p key={e.id}>{e.id} — {e.name} — {e.email} — {e.role}</p>)}</section>
 <section className="card"><h2>Leave Requests ({leaves.length})</h2>{leaves.map(l=><p key={l.id}>#{l.id} {l.employee?.name} | {l.startDate} → {l.endDate} | <b>{l.status}</b></p>)}</section></main>}
createRoot(document.getElementById('root')).render(<App/>);
