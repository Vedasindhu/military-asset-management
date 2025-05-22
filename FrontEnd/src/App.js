import React, { useState } from 'react';
import axios from 'axios';

function App() {
  const [token, setToken] = useState('');
  const [dashboard, setDashboard] = useState({});
  const [equipmentType, setEquipmentType] = useState('');
  const [purchases, setPurchases] = useState([]);

  const login = async () => {
    const res = await axios.post('http://localhost:8080/api/login', {
      username: 'admin',
      password: 'admin123'
    });
    setToken(res.data.token);
  };

  const fetchDashboard = async () => {
    const res = await axios.get('http://localhost:8080/api/dashboard', {
      headers: { Authorization: `Bearer ${token}` }
    });
    setDashboard(res.data);
  };

  const recordPurchase = async () => {
    await axios.post('http://localhost:8080/api/purchase', {
      baseId: 1,
      equipmentType: equipmentType,
      quantity: 10
    }, {
      headers: { Authorization: `Bearer ${token}` }
    });
    alert('Purchase recorded.');
  };

  const loadPurchases = async () => {
    const res = await axios.get(`http://localhost:8080/api/purchase?type=${equipmentType}`, {
      headers: { Authorization: `Bearer ${token}` }
    });
    setPurchases(res.data);
  };

  return (
    <div className="p-6">
      <button onClick={login} className="bg-blue-500 text-white p-2 rounded">Login</button>
      <button onClick={fetchDashboard} className="bg-green-500 text-white p-2 rounded ml-2">Load Dashboard</button>
      <div className="mt-4">
        <h2>Dashboard</h2>
        <pre>{JSON.stringify(dashboard, null, 2)}</pre>
      </div>
      <div className="mt-4">
        <input
          className="border p-1 mr-2"
          placeholder="Equipment Type"
          value={equipmentType}
          onChange={e => setEquipmentType(e.target.value)}
        />
        <button onClick={recordPurchase} className="bg-purple-500 text-white p-2 rounded">Add Purchase</button>
        <button onClick={loadPurchases} className="bg-yellow-500 text-black p-2 rounded ml-2">View Purchases</button>
        <pre>{JSON.stringify(purchases, null, 2)}</pre>
      </div>
    </div>
  );
}

export default App;
