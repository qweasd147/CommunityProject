import { Table, Tag, Divider } from 'antd';
import Link from 'next/link'

const columns = [
  { title: 'idx', dataIndex: 'idx', key: 'idx' }
  , { title: 'subject', dataIndex: 'subject', key: 'subject'
    , render: (text, record) => <Link href="/articles/main/[record.idx]" as={`/articles/main/${record.idx}`}><a>{text}</a></Link>
  }
  , { title: 'writeInfo', dataIndex: 'writeInfo.createdBy', key: 'writeInfo.createdBy' }
  , {
    title: 'Tags',
    key: 'tags',
    dataIndex: 'tags',
    render: tags => (
      <span>
        {tags.map(tag => {
          let color = tag.length > 5 ? 'geekblue' : 'green';
          if (tag === 'loser') {
            color = 'volcano';
          }
          return (
            <Tag color={color} key={tag}>
              {tag.toUpperCase()}
            </Tag>
          );
        })}
      </span>
    ),
  }
  , { title: 'hits', dataIndex: 'hits', key: 'hits' }
  , {
    title: 'Action',
    key: 'action',
    render: (text, record) => (
      <span>
        <a>Invite {record.subject}</a>
        <Divider type="vertical" />
        <a>Delete</a>
      </span>
    ),
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
