import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";

import Utenti from "./components/Utenti";
import Home from "./components/Home";
import TopBar from "./components/TopBar";

function App() {
  return (
    <>
      <BrowserRouter>
        <TopBar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/utenti" element={<Utenti />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
