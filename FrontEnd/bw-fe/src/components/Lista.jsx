import { Col, ListGroup, Row } from "react-bootstrap";

const Lista = ({ listaClienti }) => {
  return (
    listaClienti &&
    listaClienti.map((elem, index) => (
      <ListGroup.Item key={index}>
        <Row xs={4} className="d-flex justify-content-between align-items-center">
          <Col>{elem.nomeContatto}</Col>
          <Col>{elem.cognomeContatto}</Col>
          <Col>{elem.telefonoContatto}</Col>
        </Row>
      </ListGroup.Item>
    ))
  );
};

export default Lista;
