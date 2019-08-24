import { Table } from 'antd';

const columns = [
  { title: 'idx', dataIndex: 'idx', key: 'idx' },
  { title: 'subject', dataIndex: 'subject', key: 'subject' },
  { title: 'writeInfo', dataIndex: 'writeInfo.createdBy', key: 'writeInfo.createdBy' },
  {
    title: 'Action',
    dataIndex: '',
    key: 'x',
    render: () => <a href="javascript:;">Delete</a>,
  },
];

export default ({articles})=>{
    return (
        <Table
            columns={columns}
            expandedRowRender={record => <p style={{ margin: 0 }}>{record.contents}</p>}
            dataSource={articles}
        />
    );
}
