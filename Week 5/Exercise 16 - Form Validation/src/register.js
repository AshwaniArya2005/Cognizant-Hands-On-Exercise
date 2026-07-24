import { useState } from 'react';

const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

function validateField(name, value) {
  switch (name) {
    case 'name':
      return value.trim().length < 5 ? 'Full Name must be 5 characters long!' : '';
    case 'email':
      return emailRegex.test(value) ? '' : 'Email is not valid!';
    case 'password':
      return value.length < 8 ? 'Password must be 8 characters long!' : '';
    default:
      return '';
  }
}

function Register() {
  const [formData, setFormData] = useState({ name: '', email: '', password: '' });
  const [errors, setErrors] = useState({ name: '', email: '', password: '' });

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData((previous) => ({ ...previous, [name]: value }));
    setErrors((previous) => ({ ...previous, [name]: validateField(name, value) }));
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    const newErrors = {
      name: validateField('name', formData.name),
      email: validateField('email', formData.email),
      password: validateField('password', formData.password)
    };

    setErrors(newErrors);

    const isValid = Object.values(newErrors).every((message) => message === '');

    if (isValid) {
      alert('Valid Form');
    }
  };

  return (
    <div className="register-container">
      <h1 className="register-title">Register Here!!!</h1>
      <form onSubmit={handleSubmit} noValidate>
        <div className="form-row">
          <label htmlFor="name">Name:</label>
          <input
            id="name"
            type="text"
            name="name"
            value={formData.name}
            onChange={handleChange}
          />
        </div>
        {errors.name && <p className="error-text">{errors.name}</p>}

        <div className="form-row">
          <label htmlFor="email">Email:</label>
          <input
            id="email"
            type="text"
            name="email"
            value={formData.email}
            onChange={handleChange}
          />
        </div>
        {errors.email && <p className="error-text">{errors.email}</p>}

        <div className="form-row">
          <label htmlFor="password">Password:</label>
          <input
            id="password"
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
          />
        </div>
        {errors.password && <p className="error-text">{errors.password}</p>}

        <div className="submit-row">
          <button type="submit">Submit</button>
        </div>
      </form>
    </div>
  );
}

export default Register;
