// import { StrictMode} from 'react'
import { createRoot } from 'react-dom/client'
import React from 'react'
import {App} from './App';
import MyGlobalStyles from './styles/globalStyles';

createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <MyGlobalStyles />
    <App />
  </React.StrictMode>,
)
