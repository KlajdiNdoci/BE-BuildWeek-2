import { useEffect, useState } from "react";
import { Alert, Button, Container, Form } from "react-bootstrap";
import { Link, useNavigate, useParams } from "react-router-dom";

const Email = () => {
  const params = useParams();
  const [cliente, setCliente] = useState();
  const [oggetto, setOggetto] = useState();
  const [contenuto, setContenuto] = useState();
  const [statoMail, setStatoMail] = useState(false);
  const nav = useNavigate();
  const [cont, setcont] = useState(5);

  const getCliente = async () => {
    const aut = JSON.parse(localStorage.getItem("token"));
    console.log(aut.accessToken);
    try {
      const risp = await fetch(`http://localhost:3001/clienti/${params.id}`, {
        method: "GET",
        headers: {
          "content-type": "application/json",
          Authorization: `Bearer ${aut.accessToken}`,
        },
      });
      if (risp.ok) {
        const data = await risp.json();
        console.log(data);
        setCliente(data);
      }
    } catch (error) {
      console.log(error);
    }
  };

  const sendEmail = async () => {
    const aut = JSON.parse(localStorage.getItem("token"));
    console.log(aut.accessToken);
    try {
      const risp = await fetch(`http://localhost:3001/email/send`, {
        method: "POST",
        headers: {
          "content-type": "application/json",
          Authorization: `Bearer ${aut.accessToken}`,
        },
        body: JSON.stringify({
          destinatario: cliente.emailContatto,
          oggetto: oggetto,
          contenuto: contenuto,
        }),
      });

      if (risp.ok) {
        setContenuto("");
        setOggetto("");
        setStatoMail(true);
        let x = 5;
        const timer = setInterval(() => {
          setcont((prevcont) => prevcont - 1);
          x--;
          if (x === 0) {
            setStatoMail(false);
            clearInterval(timer);
            nav("/utenti");
          }
        }, 1000);
      } else {
        console.error("Failed to send email:", risp.statusText);
      }
    } catch (error) {
      console.error("Error sending email:", error);
    }
  };

  useEffect(() => {
    console.log("mount");
    getCliente();
  }, []);

  return (
    <>
      {statoMail && <Alert variant="success">Email inviata correttamente. Sarai reindirizzato in {cont}</Alert>}
      {cliente && (
        <Container className="mt-5">
          <Form
            onSubmit={(e) => {
              e.preventDefault();
              sendEmail();
            }}
          >
            <Form.Group className="mb-3">
              <Form.Label>Indirizzo email</Form.Label>
              <Form.Control type="email" disabled placeholder={cliente.emailContatto} />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Oggetto</Form.Label>
              <Form.Control
                type="text"
                placeholder="Oggetto"
                className="mb-3"
                required
                value={oggetto}
                onChange={(e) => {
                  setOggetto(e.target.value);
                }}
              />
              <Form.Label>Contenuto</Form.Label>
              <Form.Control
                as="textarea"
                rows={6}
                placeholder="Contenuto"
                className="mb-3"
                value={contenuto}
                required
                onChange={(e) => {
                  setContenuto(e.target.value);
                }}
              />
              <div className="d-flex justify-content-between">
                <Link to={"/utenti"} className="m-0">
                  <Button variant="secondary">Indietro</Button>
                </Link>
                <Button type="submit">Invia</Button>
              </div>
            </Form.Group>
          </Form>
        </Container>
      )}
    </>
  );
};

export default Email;
