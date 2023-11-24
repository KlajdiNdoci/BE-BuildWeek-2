import React, { useState } from "react";
import { Image } from "react-bootstrap";
import { BuildingAdd, CashCoin, Mailbox, Phone } from "react-bootstrap-icons";
import Button from "react-bootstrap/Button";
import Col from "react-bootstrap/Col";
import Container from "react-bootstrap/Container";
import Modal from "react-bootstrap/Modal";
import Row from "react-bootstrap/Row";

const ClientiDetail = (props) => {
  return (
    props.elem && (
      <Modal {...props} aria-labelledby="contained-modal-title-vcenter" className="px-1">
        {console.log(props.elem)}
        <Modal.Header closeButton>
          <Modal.Title id="contained-modal-title-vcenter">Cliente Details</Modal.Title>
        </Modal.Header>
        <Modal.Body className="grid-example">
          <Container>
            <Row>
              <Col xs={3}>
                <div className="overflow-hidden">
                  <Image src={props.elem.logoAziendale} width={"100px"} />
                </div>
              </Col>
              <Col xs={9}>
                <p style={{ fontWeight: "bold" }}>{props.elem.nomeCliente}</p>
                <p>
                  <Mailbox className="me-2" />
                  {props.elem.email}
                </p>
                <p>
                  <Phone className="me-2" />
                  {props.elem.telefono}
                </p>
                <p>
                  <CashCoin className="me-2" />
                  {props.elem.fatturatoAnnuale}€
                </p>
                {props.elem.indirizzoSedeLegale && (
                  <p>
                    <BuildingAdd className="me-2" />
                    {props.elem.indirizzoSedeLegale.via}
                    {props.elem.indirizzoSedeLegale.civico} {props.elem.indirizzoSedeLegale.comune.nome}{" "}
                    {props.elem.indirizzoSedeLegale.comune.provincia.nome}
                  </p>
                )}
                {props.elem.indirizzoSedeOperativa && (
                  <p>
                    <BuildingAdd className="me-2" />
                    {props.elem.indirizzoSedeOperativa.via}
                    {props.elem.indirizzoSedeOperativa.civico} {props.elem.indirizzoSedeOperativa.comune.nome}{" "}
                    {props.elem.indirizzoSedeOperativa.comune.provincia.nome}
                  </p>
                )}
              </Col>
            </Row>
          </Container>
        </Modal.Body>
        <Modal.Footer>
          <Button onClick={props.onHide}>Close</Button>
        </Modal.Footer>
      </Modal>
    )
  );
};
export default ClientiDetail;
