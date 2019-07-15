<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <q-page class="bg-grey-2 full-height" >

    <div class="flex items-center justify-start text-h5 text-left text-grey-9 q-pa-lg bg-grey-2 full-width"
         style="position: fixed; z-index: 2;">

      <q-icon name="home"/>
      <span class="q-ml-sm text-weight-bolder">터미널 관리</span>
    </div>

    <div style="height: 80px;"></div>

    <div class="row flex-center q-mt-lg">
      <q-card class="flex q-pa-md" style="width: 95%;">
        <div class="col flex items-center justify-start">
          <q-select class="my-searchItems-width q-mr-md" outlined v-model="searchSelectValue" :options="options" label="검색조건 선택" />
          <q-input class="my-searchItems-width q-mr-md" outlined square v-model="searchInputValue" label="검색어" placeholder="검색어를 입력하세요." :dense="dense" />
          <q-btn class="my-searchItems-height" icon="search" color="secondary" @click="searchBtnClick">조회</q-btn>
        </div>
        <div class="col-1 items-center justify-end">
          <q-btn class="my-searchItems-height" icon="add" color="indigo" @click="newRegitTermClick">신규등록</q-btn>
        </div>

      </q-card>
    </div>

    <div class="flex flex-center q-mt-lg q-mb-lg">
      <q-table
        class="my-sticky-header-table"
        style="width: 95%;"
        title="Terminal"
        :data="data"
        :columns="columns"
        row-key="id"
        :pagination.sync="pagination"
        :loading="loading"
        :filter="filter"
        @request="onRequest"
        binary-state-sort
        rows-per-page-label="페이지당 줄수"
        :table-header-style="{ backgroundColor: '#63dd9e' }"
      >
        <template v-slot:top-right>
          <q-input borderless dense debounce="300" v-model="filter" placeholder="Search">
            <template v-slot:append>
              <q-icon name="search" />
            </template>
          </q-input>
        </template>

      </q-table>

    </div>

  </q-page>
</template>

<style lang="stylus">
  .th-style {
    font-weight: 800;
    font-size: 15px;
  }

</style>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Page1',
  data () {
    return {
      filter: '',
      loading: false,

      searchSelectValue: '터미널 코드',
      options: [
        '터미널 코드',
        '터미널 명',
        '터미널 분류',
        '담당자',
        '사용여부',
        '등록자',
        '등록일자',
      ],
      searchInputValue: '',
      dense: false,


      pagination: {
        sortBy: 'name',
        descending: false,
        page: 1,
        rowsPerPage: 10,
        rowsNumber: 10
      },
      columns: [
        { name: '터미널 코드', label: '터미널 코드', field: 'termCode', sortable: false, align: 'center'},
        { name: '터미널 명', required: true, label: '터미널 명', align: 'left', field: row => row.termName, format: val => `${val}`, sortable: true },
        { name: '터미널 분류', label: '터미널 분류', field: 'termKind', sortable: false },
        { name: '담당자', label: '담당자', field: 'termPerson', sortable: true },
        { name: '사용여부', label: '사용여부', field: 'useable', sortable: false },
        { name: '등록자', label: '등록자', field: 'regPerson', sortable: false },
        { name: '등록일자', label: '등록일자', field: 'regDate', sortable: true, sort: (a, b) => parseInt(a, 10) - parseInt(b, 10) }
      ],
      data: [],
      original: [
        { id: 1, termCode: 'TR00001', termName: '동서울', termKind: '시외', termPerson: '김서울', useable: 'Y', regPerson: '홍길동', regDate: '2019-07-01'},
        { id: 2, termCode: 'TR00002', termName: '대구', termKind: '복합', termPerson: '강구울', useable: 'Y', regPerson: '홍길동', regDate: '2019-04-09'},
        { id: 3, termCode: 'TR00003', termName: '부산', termKind: '고속', termPerson: '백두산', useable: 'N', regPerson: '홍길동', regDate: '2019-02-18'},

        { id: 4, termCode: 'TR00001', termName: '동서울', termKind: '시외', termPerson: '김서울', useable: 'N', regPerson: '홍길동', regDate: '2019-07-01'},
        { id: 5, termCode: 'TR00002', termName: '대구', termKind: '복합', termPerson: '강구울', useable: 'Y', regPerson: '홍길동', regDate: '2019-04-09'},
        { id: 6, termCode: 'TR00003', termName: '부산', termKind: '고속', termPerson: '백두산', useable: 'Y', regPerson: '홍길동', regDate: '2019-02-18'},

        { id: 7, termCode: 'TR00001', termName: '동서울', termKind: '시외', termPerson: '김서울', useable: 'N', regPerson: '홍길동', regDate: '2019-07-01'},
        { id: 8, termCode: 'TR00002', termName: '대구', termKind: '복합', termPerson: '강구울', useable: 'N', regPerson: '홍길동', regDate: '2019-04-09'},
        { id: 9, termCode: 'TR00003', termName: '부산', termKind: '고속', termPerson: '백두산', useable: 'Y', regPerson: '홍길동', regDate: '2019-02-18'},

        { id: 10, termCode: 'TR00001', termName: '동서울', termKind: '시외', termPerson: '김서울', useable: 'Y', regPerson: '홍길동', regDate: '2019-07-01'},
        { id: 11, termCode: 'TR00002', termName: '대구', termKind: '복합', termPerson: '강구울', useable: 'Y', regPerson: '홍길동', regDate: '2019-04-09'},
        { id: 12, termCode: 'TR00003', termName: '부산', termKind: '고속', termPerson: '백두산', useable: 'Y', regPerson: '홍길동', regDate: '2019-02-18'},

        { id: 10, termCode: 'TR00001', termName: '동서울', termKind: '시외', termPerson: '김서울', useable: 'Y', regPerson: '홍길동', regDate: '2019-07-01'},
        { id: 11, termCode: 'TR00002', termName: '대구', termKind: '복합', termPerson: '강구울', useable: 'Y', regPerson: '홍길동', regDate: '2019-04-09'},
        { id: 12, termCode: 'TR00003', termName: '부산', termKind: '고속', termPerson: '백두산', useable: 'Y', regPerson: '홍길동', regDate: '2019-02-18'},
      ]
    }
  },
  computed: {
    ...mapGetters([
      'getLayout'
    ])
  },
  methods: {
    onRequest (props) {
      let { page, rowsPerPage, rowsNumber, sortBy, descending } = props.pagination
      let filter = props.filter

      this.loading = true

      // emulate server
      setTimeout(() => {
        // update rowsCount with appropriate value
        this.pagination.rowsNumber = this.getRowsNumberCount(filter)

        // get all rows if "All" (0) is selected
        let fetchCount = rowsPerPage === 0 ? rowsNumber : rowsPerPage

        // calculate starting row of data
        let startRow = (page - 1) * rowsPerPage

        // fetch data from "server"
        let returnedData = this.fetchFromServer(startRow, fetchCount, filter, sortBy, descending)

        // clear out existing data and add new
        this.data.splice(0, this.data.length, ...returnedData)

        // don't forget to update local pagination object
        this.pagination.page = page
        this.pagination.rowsPerPage = rowsPerPage
        this.pagination.sortBy = sortBy
        this.pagination.descending = descending

        // ...and turn of loading indicator
        this.loading = false
      }, 1000)
    },

    // emulate ajax call
    // SELECT * FROM ... WHERE...LIMIT...
    fetchFromServer (startRow, count, filter, sortBy, descending) {
      let data = []

      if (!filter) {
        data = this.original.slice(startRow, startRow + count)
      }
      else {
        let found = 0
        for (let index = startRow, items = 0; index < this.original.length && items < count; ++index) {
          let row = this.original[index]
          // match filter?
          if (!row['name'].includes(filter)) {
            // get a different row, until one is found
            continue
          }
          ++found
          if (found >= startRow) {
            data.push(row)
            ++items
          }
        }
      }

      // handle sortBy
      if (sortBy) {
        data.sort((a, b) => {
          let x = descending ? b : a
          let y = descending ? a : b
          if (sortBy === 'desc') {
            // string sort
            return x[sortBy] > y[sortBy] ? 1 : x[sortBy] < y[sortBy] ? -1 : 0
          }
          else {
            // numeric sort
            return parseFloat(x[sortBy]) - parseFloat(y[sortBy])
          }
        })
      }

      return data
    },

    // emulate 'SELECT count(*) FROM ...WHERE...'
    getRowsNumberCount (filter) {
      if (!filter) {
        return this.original.length
      }
      let count = 0
      this.original.forEach((treat) => {
        if (treat['name'].includes(filter)) {
          ++count
        }
      })
      return count
    },


    searchBtnClick(){},
    newRegitTermClick(){},

  },
  beforeCreate () {},
  created () {},
  beforeMount () {
    // let breadcrumbs = this.getLayout.$refs.breadcrumbs
  },
  mounted () {

    this.onRequest({
      pagination: this.pagination,
      filter: undefined
    })
  },
  beforeUpdate () {},
  updated () {},
  beforeDestroy () {},
  destroyed () {}
}
</script>

<style lang="stylus">
  .my-sticky-header-table
    .q-table__middle
      max-height auto

    .q-table__top,
    thead tr:first-child th /* bg color is important for th; just specify one */
      background-color #a3f4d3

    .q-table__bottom
      background-color #ebebeb

    thead tr:first-child th
      position sticky
      top 0
      opacity 1
      z-index 1
      font-size 15px
      font-weight bolder

  .my-searchItems-width
    width: 180px;
  .my-searchItems-height
    height: 50px;

</style>
