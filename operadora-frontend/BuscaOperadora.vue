<template>
  <div>
    <h1>Buscar Operadoras</h1>
    <input v-model="termo" type="text" placeholder="Digite o nome da operadora" />
    <button @click="buscarOperadoras">Buscar</button>

    <div v-if="operadoras.length > 0">
      <ul>
        <li v-for="operadora in operadoras" :key="operadora.id">
          {{ operadora.nomeOperadora }} - {{ operadora.cnpj }}
        </li>
      </ul>
    </div>
    <div v-else>
      <p>Nenhuma operadora encontrada.</p>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      termo: "",
      operadoras: [],
    };
  },
  methods: {
    async buscarOperadoras() {
      try {
        const response = await axios.get(`http://localhost:8080/api/operadoras/buscar?termo=${this.termo}`);
        this.operadoras = response.data;
      } catch (error) {
        console.error("Erro ao buscar operadoras", error);
      }
    },
  },
};
</script>

<style scoped>
/* Adicione seu estilo aqui */
</style>
