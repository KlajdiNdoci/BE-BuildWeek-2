import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";

import Utenti from "./components/Utenti";
import Home from "./components/Home";
import TopBar from "./components/TopBar";
import Fatture from "./components/Fatture";
import Email from "./components/Email";

function App() {
  return (
    <>
      <BrowserRouter>
        <TopBar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/utenti" element={<Utenti />} />
          <Route path="/fatture" element={<Fatture />} />
          <Route path="/email/:id" element={<Email />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
