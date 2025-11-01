<template>
  <div>
    <form @submit.prevent="cadastrar">
      <p>
        <label id="labelTexto">Texto: </label>
        <input id="texto" type="text" v-model="novaAnotacao.texto" />
      </p>
      <p>
        <label id="labelDataHora">Data/Hora: </label>
        <input id="dataHora" type="datetime-local" v-model="novaAnotacao.dataHora" />
      </p>
      <p>
        <label id="labelUsuario">Usuário: </label>
        <select id="usuario" v-model="novaAnotacao.usuario.id">
          <option v-for="usuario in usuarios" :key="usuario.id" :value="usuario.id">
            {{ usuario.nome }}
          </option>
        </select>
        <input type="number" v-model="novaAnotacao.usuario.id" />
      </p>
      <button type="submit">Cadastrar</button>
    </form>
    <form @submit.prevent="pesquisar">
      <p>
        <label id="labelTextoPesquisa">Texto: </label>
        <input id="textoPesquisa" type="text" v-model="pesquisa.texto" />
      </p>
      <p>
        <label id="labelUsuarioPesquisa">Usuário: </label>
        <select id="usuarioPesquisa" v-model="pesquisa.usuario.nome">
          <option v-for="usuario in usuarios" :key="usuario.id" :value="usuario.nome">
            {{ usuario.nome }}
          </option>
        </select>
      </p>
      <button type="submit">Pesquisar</button>
    </form>
    <p v-if="erro">{{ erro }}</p>
    <table>
      <thead>
        <tr>
          <th>Id</th>
          <th>Texto</th>
          <th>Data/Hora</th>
          <th>Usuário</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="anotacao in anotacoes" :key="anotacao.id">
          <th>{{ anotacao.id }}</th>
          <th>{{ anotacao.texto }}</th>
          <th>{{ anotacao.dataHora }}</th>
          <th>{{ anotacao.usuario.nome }}</th>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
import { onMounted } from 'vue'

interface usuario {
  id: number
  nome?: string
  senha?: string
}

interface anotacao {
  id?: number
  texto: string
  dataHora: Date
  usuario: usuario
}

const novaAnotacao = ref<anotacao>({ texto: '', dataHora: new Date(), usuario: { id: 1 } })
const pesquisa = ref<anotacao>({ texto: '', dataHora: new Date(), usuario: { id: 1 } })

const anotacoes = ref<anotacao[]>()

const usuarios = ref<usuario[]>()

const erro = ref<string>()

async function pesquisar() {
  anotacoes.value = (
    await axios.get('anotacao/textoEUsuario', {
      params: {
        texto: pesquisa.value.texto,
        usuario: pesquisa.value.usuario.nome,
      },
    })
  ).data
}

async function cadastrar() {
  try {
    await axios.post('anotacao', novaAnotacao.value)
    atualizar()
    novaAnotacao.value.texto = ''
    novaAnotacao.value.dataHora = new Date()
    novaAnotacao.value.usuario.id = 1
    erro.value = undefined
  } catch (e) {
    erro.value = (e as Error).message
  }
}

async function atualizar() {
  anotacoes.value = (await axios.get('anotacao')).data
}

async function buscarUsuarios() {
  usuarios.value = (await axios.get('usuario')).data
}

onMounted(() => {
  atualizar()
  buscarUsuarios()
})
</script>
